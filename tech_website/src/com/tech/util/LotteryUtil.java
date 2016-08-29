package com.tech.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tech.pojo.Latestlottery;
import com.tech.pojo.Lotteryproductutil;
import com.tech.pojo.Product;
import com.tech.pojo.Randomnumber;
import com.tech.pojo.Spellbuyproduct;
import com.tech.pojo.Spellbuyrecord;
import com.tech.pojo.User;
import com.tech.service.LatestlotteryService;
import com.tech.service.LotteryproductutilService;
import com.tech.service.ProductService;
import com.tech.service.RandomnumberService;
import com.tech.service.SpellbuyproductService;
import com.tech.service.SpellbuyrecordService;
import com.tech.sms.SmsSenderFactory;
import com.tech.util.weixin.WxUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext*.xml" })
@Repository
public class LotteryUtil {
	private static final Logger LOG = LoggerFactory.getLogger(LotteryUtil.class);

	@Autowired
	private SpellbuyrecordService spellbuyrecordService;
	@Autowired
	private SpellbuyproductService sbpService;
	@Autowired
	private LatestlotteryService latestlotteryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private RandomnumberService randomnumberService;
	@Autowired
	private LotteryproductutilService lotterypuService;
	// props
	private final static int LOTSZ = 3;
	private final BlockingQueue<Lotter> lotQueue = new ArrayBlockingQueue<Lotter>(LOTSZ);
	{
		try {
			for (int i = LOTSZ - 1; i >= 0; --i) {
				final Lotter lot = new Lotter(i + 1);
				lot.start();
				lotQueue.put(lot);
			}
		} catch (final InterruptedException e) {
			// ignore!
		}
	}

	private class Lotter extends Thread {

		private String pid;

		public Lotter(final int id) {
			setName("lotter-" + id);
			setDaemon(true);
		}

		@Override
		public void run() {
			LOG.info("Started");
			for (;;) {
				try {
					final String id;
					synchronized (this) {
						for (; pid == null;) {
							wait();
						}
						id = pid;
						pid = null;
					}
					lottery(id);
				} catch (final InterruptedException e) {
					continue;
				} catch (final RuntimeException e) {
					LOG.error("{}", e);
				} finally {
					try {
						lotQueue.put(this);
					} catch (InterruptedException e) {
					}
				}
			}
		}

		public synchronized void assign(final String pid) {
			this.pid = pid;
			notifyAll();
		}

	}

	@Test
	public void go() {
		try {
			System.out.println("--go-");
			// all?! Only lottery-able
			List<Lotteryproductutil> lpus = lotterypuService
					.loadAll(Spellbuyproduct.STATUS_LOTABLE);
			for (final Lotteryproductutil lpu : lpus) {
				final String edate = lpu.getLotteryProductEndDate();
				final long now = System.currentTimeMillis();
				final long end = DateUtil.SDateTimeToDate(edate).getTime();
				if ((end - now) / 1000L <= 0L) {
					final Lotter lot = lotQueue.take();
					lot.assign(lpu.getLotteryProductId().toString());
				}
			}
		} catch (final InterruptedException e) {
			LOG.info("<EXIT>: INT");
		}
	}
/*
 * 满了开奖
 * 易购OS 易购OS
 * 杨振朋
	*/
	@SuppressWarnings("unchecked")
	public void lottery(final String id) {
		//spellbuyProductId
		final Integer iid = Integer.valueOf(id);
		final Spellbuyproduct sbp = ((Spellbuyproduct) sbpService.findById(id));
		// 必须处于开奖状态
		if (sbp.isLotteryable()) {
			// calc: lastest 100 buy-date sum
			long dateSum = 0L;
			Spellbuyrecord sbRecored = ((Spellbuyrecord) spellbuyrecordService
					.getEndBuyDateByProduct(iid).get(0));
			final String endDate = sbRecored.getBuyDate();
			final int LNR = 100;
			List<Spellbuyrecord> dataList = spellbuyrecordService
					.getSpellbuyRecordByLast100(null, endDate, LNR);
			final Calendar calendar = Calendar.getInstance();
			StringBuilder sbuf = new StringBuilder();
			long beginTime=0, endTime=0;
			for (int k = 0, size = dataList.size(); k < size; k++) {
				final Spellbuyrecord record = (Spellbuyrecord) dataList.get(k);
				calendar.setTime(DateUtil.SDateTimeToDateBySSS(record.getBuyDate()));
				final int h = calendar.get(Calendar.HOUR_OF_DAY);
				final int m = calendar.get(Calendar.MINUTE);
				final int s = calendar.get(Calendar.SECOND);
				final int ms = calendar.get(Calendar.MILLISECOND);
				sbuf.setLength(0);
				if (h < 10) {
					sbuf.append('0').append(h);
				} else {
					sbuf.append(h);
				}
				if (m < 10) {
					sbuf.append('0').append(m);
				} else {
					sbuf.append(m);
				}
				if (s < 10) {
					sbuf.append('0').append(s);
				} else {
					sbuf.append(s);
				}
				if (ms < 10) {
					sbuf.append('0').append('0').append(ms);
				} else if (ms < 100) {
					sbuf.append('0').append(ms);
				} else {
					sbuf.append(ms);
				}
				dateSum += Long.parseLong(sbuf.toString());
				if(k==0){
					endTime = Long.parseLong(sbuf.toString());
				}else if(k==dataList.size()-1){
					beginTime = Long.parseLong(sbuf.toString());
				}
			}
			sbuf = null;
			LOG.info("date-sum: {} for spell buy product {}", dateSum, id);
			// calc: win numb
			int wnr = (int) (dateSum % sbp.getSpellbuyPrice().intValue() + 10000001L);
			LOG.info("win-numb: {} for spell buy product {}", wnr, id);
			//约定中奖会员处理
			final Integer pid = sbp.getFkProductId();
			final Product product = ((Product) productService.findById("" + pid));
			final String winner = product.getWinner();
			final List<String> orands = new ArrayList<String>();
			if(!StringUtil.isEmpty(winner)){
				List<Object[]> winList = spellbuyrecordService.getRandomByProductAndUser(iid, winner);
				for (final Object[] win : winList) {
					Randomnumber rn = (Randomnumber)win[0];
					if (rn.getRandomNumber().indexOf(',') != -1) {
						final String[] rs = rn.getRandomNumber().split(",");
						for (String string : rs) {
							orands.add(string);
						}
					} else {
						orands.add(rn.getRandomNumber());
					}
				}
				Collections.sort(orands);
			}
			int wnr2 = wnr;
			boolean match=false;
			if(orands.size()>0){
				for (int i = 0; i < orands.size(); i++) {
					int rand = Integer.parseInt(orands.get(i));
					if(rand>wnr){
						wnr2 = (i==0 ? rand : Integer.parseInt(orands.get(i-1)));
						match = true;
						break;
					}
				}
				if(!match){
					wnr2 = Integer.parseInt(orands.get(orands.size()-1));
				}
				//根据约定的中奖结果来调整时间
				int diff = wnr2-wnr;
				int step = 600, num=0, remain=0;
				if(diff>0){
					//时间往后调
					num = diff/step;
					remain = diff - (num*step);
				}else if(diff<0){
					//时间往前调
					step = -1*step;
					num = diff/step;
					remain = diff - (num*step);
				}
				Date newDate;
				if(num>0 && num<90){
					int adjust=0;
					for (int k = 10; k < num+10; k++) {
						final Spellbuyrecord record = (Spellbuyrecord) dataList.get(k);
						
						if(k==num+9){
							long time = DateUtil.SDateTimeToDateBySSS(record.getBuyDate()).getTime()+step+remain;
							newDate = new Date(time);
						}else{
							long time = DateUtil.SDateTimeToDateBySSS(record.getBuyDate()).getTime()+step;
							newDate = new Date(time);
						}
						record.setBuyDate(DateUtil.DateTimeToStrBySSS(newDate));
						spellbuyrecordService.add(record);
					}
				}else if(num==0 && remain!=0){
					final Spellbuyrecord record = (Spellbuyrecord) dataList.get(5);
					long time = DateUtil.SDateTimeToDateBySSS(record.getBuyDate()).getTime();
					newDate = new Date(time+remain);
					record.setBuyDate(DateUtil.DateTimeToStrBySSS(newDate));
					spellbuyrecordService.add(record);
				}
				wnr = wnr2;
			}
			
			// query: winner
			List<Object[]> objList = null;
			Spellbuyrecord wrecord = null;
			User user = null;
			/*if(product.getProductPrice()<3000){
				objList = spellbuyrecordService.randomByBuyHistoryByspellbuyProductIdV2(iid, wnr2 + "");
			}else{
				objList = spellbuyrecordService.randomByBuyHistoryByspellbuyProductId(iid, wnr2 + "");
			}*/
			objList = spellbuyrecordService.randomByBuyHistoryByspellbuyProductIdV2(iid, wnr2 + "");
			Object[] objs = (Object[])objList.get(0);
	    	for(Object obj:objs){
	    		if(obj instanceof Spellbuyrecord){
	    			wrecord = (Spellbuyrecord)obj;
	    		}
	    		if(obj instanceof User){
	    			user = (User)obj;
	    		}
	    	}
			//Spellbuyrecord wrecord = (Spellbuyrecord) ((Object[]) objList.get(0))[1];
			//final User user = ((User) ((Object[]) objList.get(0))[2]);
	    	/*if(!StringUtil.isEmpty(user.getWeixinOpenId())){
	    		
	    		JSONObject json = new JSONObject();
	    		json.put("touser", user.getWeixinOpenId());
	    		json.put("template_id", "X3i2Kn2qoEt-Oqtjj-j51rUV6Nxm5AiMvkbVCouu1DI");
	    		json.put("url", "http://www.163.com");
	    		
	    		final String result = WxUtil.send(json.toString());
	    	}else */if (user.getPhone() != null
					&& User.getMagic().equals(user.getUserPwd()) == false) {
				try {
					String content = ApplicationListenerImpl.sysConfigureJson.getLotteryMsgTpl();
					//Sampler.sendOnce(user.getPhone(), "恭喜您成为(第"+sbp.getProductPeriod()+"期)"+product.getProductName()+"的获得者，请您及时登录系统确认收货地址。");
					SmsSenderFactory.create().send(user.getPhone(), content);
				} catch (final Exception e) {
					LOG.error("Send sms error: {}", e.getMessage());
				}
			}
			// 开奖期 -> 揭晓期
			wrecord.setSpRandomNo(wnr2 + "");
			wrecord.setSpWinningStatus(Spellbuyrecord.WINSTAT_WON + "");
			wrecord.setBuyStatus(Spellbuyrecord.BUYSTAT_FINISHED + "");
			spellbuyrecordService.add(wrecord);
			sbp.setSpStatus(Spellbuyproduct.STATUS_ANNABLE);
			sbpService.add(sbp);
			// 下一期在购买结束时自动生成，这里不需生成下一期。购买结束时自动生成可能会失败？
			
			// 揭晓
			final List<?> list = latestlotteryService
					.getLatestlotteryBySpellbuyProductIdAndProductIdIsExist(iid);
			if (list.size() == 0) {
				final Latestlottery lalo = new Latestlottery();
				lalo.setProductId(pid);
				lalo.setProductName(product.getProductName());
				lalo.setProductTitle(product.getProductTitle());
				lalo.setProductPrice(sbp.getSpellbuyPrice());
				lalo.setProductImg(product.getHeadImage());
				lalo.setProductPeriod(sbp.getProductPeriod());
				lalo.setProductType(product.getProductType());
				lalo.setAnnouncedTime(endDate);
				lalo.setAnnouncedType(sbp.getSpellbuyType());
				lalo.setDateSum(dateSum);
				lalo.setBuyTime(wrecord.getBuyDate());
				lalo.setSpellbuyRecordId(wrecord.getSpellbuyRecordId());
				lalo.setSpellbuyProductId(wrecord.getFkSpellbuyProductId());
				BigDecimal buyCount = randomnumberService.RandomNumberByUserBuyCount("" + user.getUserId(), iid);
				lalo.setBuyNumberCount(Integer.valueOf(buyCount.intValue()));
				buyCount = null;
				lalo.setRandomNumber(wnr2);
				//lalo.setLocation(user.getIpLocation());
				lalo.setLocation(wrecord.getBuyLocal());
				lalo.setUserId(user.getUserId());
				lalo.setUserName(UserNameUtil.userName(user));
				lalo.setUserFace(user.getFaceImg());
				lalo.setStatus(Integer.valueOf(1));
				lalo.setShareStatus(Integer.valueOf(-1));
				lalo.setShareId(null);
				String dingdanhao=pid+""+wnr2+""+user.getUserId()+""+Math.abs(new Random().nextInt());
				lalo.setDingdanhao(dingdanhao);
				latestlotteryService.add(lalo);
			}
		}
	}
	public static void main(String[] aa){
		System.out.println(Math.abs(new Random().nextInt()));
	}

}
