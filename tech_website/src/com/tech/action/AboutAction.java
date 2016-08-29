package com.tech.action;

import org.springframework.stereotype.Component;

import com.tech.util.ApplicationListenerImpl;
import com.tech.util.Struts2Utils;

/**
 * 
 * @author bobo
 *
 */
@Component("AboutAction")
public class AboutAction extends BaseAction {
	private static final long serialVersionUID = -6979064905632902960L;

	public String index() {
		if (!ApplicationListenerImpl.sysConfigureAuth) {
			Struts2Utils
					.renderHtml(
							"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><title>授权过期 1元夺宝开发中心</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div align=\"center\" style=\"color: #f60;font-family: arial,微软雅黑;font-size: 18px;margin-top: 180px;\">该系统授权已过期，请联系管理员重新授权！谢谢合作。<a href=\"http://www.egouos.com\">www.egouos.com</a></div></body></html>",
							new String[0]);

			return null;
		}
		return "index";
	}

	public String jobs() {
		if (!ApplicationListenerImpl.sysConfigureAuth) {
			Struts2Utils
					.renderHtml(
							"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><title>授权过期 1元夺宝开发中心</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div align=\"center\" style=\"color: #f60;font-family: arial,微软雅黑;font-size: 18px;margin-top: 180px;\">该系统授权已过期，请联系管理员重新授权！谢谢合作。<a href=\"http://www.egouos.com\">www.egouos.com</a></div></body></html>",
							new String[0]);

			return null;
		}
		return "jobs";
	}

	public String contactus() {
		if (!ApplicationListenerImpl.sysConfigureAuth) {
			Struts2Utils
					.renderHtml(
							"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><title>授权过期 1元夺宝开发中心</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div align=\"center\" style=\"color: #f60;font-family: arial,微软雅黑;font-size: 18px;margin-top: 180px;\">该系统授权已过期，请联系管理员重新授权！谢谢合作。<a href=\"http://www.egouos.com\">www.egouos.com</a></div></body></html>",
							new String[0]);

			return null;
		}
		return "contactus";
	}

	public String Links() {
		if (!ApplicationListenerImpl.sysConfigureAuth) {
			Struts2Utils
					.renderHtml(
							"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><title>授权过期 1元夺宝开发中心</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div align=\"center\" style=\"color: #f60;font-family: arial,微软雅黑;font-size: 18px;margin-top: 180px;\">该系统授权已过期，请联系管理员重新授权！谢谢合作。<a href=\"http://www.egouos.com\">www.egouos.com</a></div></body></html>",
							new String[0]);

			return null;
		}
		return "Links";
	}
}
