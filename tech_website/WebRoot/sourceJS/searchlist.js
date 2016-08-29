$(document).ready(function () {
   		var id = $("#topid").val();
   		var keyword = $("#keyword").val();
	   	if(id=="hot20"){
	   		$("#hot").attr("class","click");
	   	}else if(id=="date20"){
	   		$("#new").attr("class","click");
	   	}else if(id=="price20"){
	   		$("#money").attr("class","click");
	   		$("#money").attr("href",$www+"/search/priceAsc20/"+keyword+".html");
	   	}else if(id=="priceAsc20"){
	   		$("#money").attr("class","click1");
	   		$("#money").attr("href",$www+"/search/price20/"+keyword+".html");
	   	}
	$(".scrollLoading").scrollLoading();
});