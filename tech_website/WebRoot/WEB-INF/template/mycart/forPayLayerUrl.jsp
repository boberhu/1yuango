<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  </head>
  
  <body>
 <body >
<script> 
window.onload = function(){
    var searchUrl = window.location.search,
        wyplus_ctrl = parent.parent.WYPLUS;

    if(searchUrl.indexOf('urlStr=') > 0){
        wyplus_ctrl.close();
        var urlStr = searchUrl.split("urlStr=");
        urlStr = urlStr[1];
        wyplus_ctrl.redirect(urlStr); 
    }else if(searchUrl.indexOf('reStart=1') > 0){ 
        wyplus_ctrl.repay(); 
    }else{
        wyplus_ctrl.close();
    }
}
</script>
</body>
</html>
