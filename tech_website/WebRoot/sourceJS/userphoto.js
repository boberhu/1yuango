$(function () {
	var x1 = $('#x1');
	var y1 = $('#y1');
	var w = $('#w');
	var h = $('#h');
	var userId = $("#userId").val();
	var hidPicUrl = $("#hidPicUrl");
	 $("#myFile").change(function(){
	 	var myFile = $("#myFile").val();
	 	myFile = myFile.toLowerCase()
	 	if(myFile.lastIndexOf("png")==-1 && myFile.lastIndexOf("jpg")==-1 && myFile.lastIndexOf("gif")==-1 && myFile.lastIndexOf("bmp")==-1 && myFile.lastIndexOf("jpeg")==-1){
			$("#myFile").val("");
			alert("请选择图片类别的文件上传.");
			return false;
		}
      	if($("#myFile").val() != '') $("#pageForm").submit();
      	$("#changeLoading").show();
      	$("#addImgFile").attr("value","上传中...").attr("disabled","disabled");
      });
      $("#exec_target").load(function(){
         var data = $(window.frames['exec_target'].document.body).text();
         //若iframe携带返回数据，则显示在feedback中
         if(data != null){
         	$("#changeLoading").hide();
         	$("#addImgFile").attr("value","点击添加图片").attr("disabled","");
         	$("#btnSavePhoto").attr("disabled","");
			$(".frameCut").empty().append("<img width=\"300\" id=\"target\" height=\"300\"  src=\""+$img+data+"\" />");
         	$("#preview").attr("src",data);
            $("#myFile").val('');
            $("#hidPicUrl").val(data);
            var c = {"x":40,"y":45,"x2":255,"y2":107};
            $('#target').Jcrop({
		        onChange: updatePreview,
		        onSelect: updatePreview,
		        minSize: [ 100, 100 ],
            	maxSize: [ 300, 300 ],
            	setSelect: [c.x,c.y,c.x2,c.y2],
		        aspectRatio: 1
		      },function(){
		        // Use the API to get the real image size
		        var bounds = this.getBounds();
		        boundx = bounds[0];
		        boundy = bounds[1];
		        // Store the API in the jcrop_api variable
		        jcrop_api = this;
		      });
         }
     });
     
     var jcrop_api, boundx, boundy;
      function updatePreview(c){
        if (parseInt(c.w) > 0){
          var rx = 100 / c.w;
          var ry = 100 / c.h;
          $('#preview').css({
            width: Math.round(rx * boundx) + 'px',
            height: Math.round(ry * boundy) + 'px',
            marginLeft: '-' + Math.round(rx * c.x) + 'px',
            marginTop: '-' + Math.round(ry * c.y) + 'px'
          });
         	x1.val(Math.round(rx * c.x));
	      	y1.val(Math.round(ry * c.y));
	      	w.val(c.w);
	      	h.val(c.h);
        }
      };
      
      $("#btnSavePhoto").click(function(){
      	var url = "/user/updateFace.action?userId="+userId+"&x1="+parseInt(x1.val())+"&y1="+parseInt(y1.val())+"&w="+parseInt(w.val())+"&h="+parseInt(h.val())+"&hidPicUrl="+hidPicUrl.val();
      	$.ajax({
      		url:url,
      		type:"post",
      		data:"string",
      		success:function(data){
      			if(data=="true"){
      				pageDialog.PageDialog("<dl class='sAltOK'><dd>\u64cd\u4f5c\u6210\u529f\uff01</dd></dl>","",160,50,"true","true");
      				window.setInterval("location.replace(location.href);",2000);
      			}else{
      				alert("操作失败,请稍后再试！");
      				window.setInterval("location.replace(location.href);",2000);
      			}
      		}
      	});
      });
      
});

