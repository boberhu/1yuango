$(document).ready(function () {
  		var id = $("#topid").val();
  		var typeId = $("#typeId").val();
  		var typeName = $("#typeName").val();
	   	if(id=="hot20"){
	   		$("#hot").attr("class","click");
	   	}else if(id=="date20"){
	   		$("#new").attr("class","click");
	   	}else if(id=="price20"){
	   		$("#money").attr("class","click");
	   		if(typeId!=null && typeId!=""){
	   			$("#money").attr("href",$www+"/list/priceAsc20/"+typeId+".html");
	   		}else{
		   		$("#money").attr("href",$www+"/list/priceAsc20.html");
	   		}
	   	}else if(id=="priceAsc20"){
	   		$("#money").attr("class","click1");
	   		if(typeId!=null && typeId!=""){
	   			$("#money").attr("href",$www+"/list/price20/"+typeId+".html");
	   		}else{
		   		$("#money").attr("href",$www+"/list/price20.html");
	   		}
	   	}
	   	$(".scrollLoading").scrollLoading();
});