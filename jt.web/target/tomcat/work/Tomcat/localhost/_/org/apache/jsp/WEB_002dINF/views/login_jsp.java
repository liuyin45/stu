/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-07-14 03:50:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\"/>\r\n");
      out.write("    <title>登录京淘</title>\r\n");
      out.write("    <link type=\"text/css\" rel=\"stylesheet\" href=\"/css/login.css\"/>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"/js/jquery-1.2.6.min.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"/js/login/g.base.js\"></script>\r\n");
      out.write("   \t<script type=\"text/javascript\" src=\"/js/login/jdEdit.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("        var pgeditor = new jQuery.pge({\r\n");
      out.write("            pgePath: \"#\",\r\n");
      out.write("            pgeId: \"_ocx_password\",\r\n");
      out.write("            pgeEdittype: 0,\r\n");
      out.write("            pgeEreg1: \"\",\r\n");
      out.write("            pgeEreg2: \"\",\r\n");
      out.write("            pgeMaxlength: 20,\r\n");
      out.write("            pgeTabindex: 2,\r\n");
      out.write("            pgeClass: \"text_pge\",\r\n");
      out.write("            pgeInstallClass: \"text_pge\",\r\n");
      out.write("            pgeOnkeydown:\"$('#loginsubmit').click();\",\r\n");
      out.write("            tabCallback:\"authcode\"\r\n");
      out.write("        });\r\n");
      out.write("        window.onload = function(){\r\n");
      out.write("            pgeditor.pgInitialize();\r\n");
      out.write("        }\r\n");
      out.write("    </script>\r\n");
      out.write("        <script type=\"text/javascript\">\r\n");
      out.write("            $(function(){\r\n");
      out.write("                if(pgeditor.checkInstall()){\r\n");
      out.write("                    $(\"#chkOpenCtrl\").attr(\"checked\",true);\r\n");
      out.write("                    $(\"#nloginpwd\").hide();\r\n");
      out.write("                    $(\"#sloginpwd\").show();\r\n");
      out.write("                    if(pgeditor.checkUpdate()==1){\r\n");
      out.write("                        $(\"#updata\").show();\r\n");
      out.write("                    }\r\n");
      out.write("                }\r\n");
      out.write("            })\r\n");
      out.write("        </script>\r\n");
      out.write("    \r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"w\">\r\n");
      out.write("    <div id=\"logo\">\r\n");
      out.write("    \t<a href=\"http://www.jt.com/\" clstag=\"passport|keycount|login|01\">\r\n");
      out.write("    \t\t<img src=\"/images/jt-logo.png\" alt=\"京淘\" width=\"170\" height=\"60\"/>\r\n");
      out.write("    \t</a><b></b>\r\n");
      out.write("   \t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<form id=\"formlogin\" method=\"post\" onsubmit=\"return false;\">\r\n");
      out.write("    <input type=\"hidden\" id=\"uuid\" name=\"uuid\" value=\"1359c13d-7daa-4a2a-972d-f09e09e6605a\"/>\r\n");
      out.write("    <div class=\" w1\" id=\"entry\">\r\n");
      out.write("        <div class=\"mc \" id=\"bgDiv\">\r\n");
      out.write("            <div id=\"entry-bg\" clstag=\"passport|keycount|login|02\" style=\"width: 511px; height: 455px; position: absolute; left: -44px; top: -44px; background: url(/images/544a11d3Na5a3d566.png) 0px 0px no-repeat;\">\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("            <div class=\"form \">\r\n");
      out.write("                <div class=\"item fore1\">\r\n");
      out.write("                    <span>用户名/已验证手机</span>\r\n");
      out.write("                    <div class=\"item-ifo\">\r\n");
      out.write("                        <input type=\"text\" id=\"loginname\" name=\"username\" class=\"text\"  tabindex=\"1\" autocomplete=\"off\"/>\r\n");
      out.write("                        <div class=\"i-name ico\"></div>\r\n");
      out.write("                        <label id=\"loginname_succeed\" class=\"blank invisible\"></label>\r\n");
      out.write("                        <label id=\"loginname_error\" class=\"hide\"><b></b></label>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <script type=\"text/javascript\">\r\n");
      out.write("                    setTimeout(function () {\r\n");
      out.write("                        if (!$(\"#loginname\").val()) {\r\n");
      out.write("                            $(\"#loginname\").get(0).focus();\r\n");
      out.write("                        }\r\n");
      out.write("                    }, 0);\r\n");
      out.write("                </script>\r\n");
      out.write("                <div id=\"capslock\"><i></i><s></s>键盘大写锁定已打开，请注意大小写</div>\r\n");
      out.write("                <div class=\"item fore2\">\r\n");
      out.write("                    <span>密码</span>\r\n");
      out.write("                    <div class=\"item-ifo\">\r\n");
      out.write("                        <label id=\"sloginpwd\" style=\"display: none;\">\r\n");
      out.write("                            <script type=\"text/javascript\">pgeditor.generate()</script>\r\n");
      out.write("                        </label>\r\n");
      out.write("                        <input type=\"password\" id=\"nloginpwd\" name=\"password\" class=\"text\" tabindex=\"2\" autocomplete=\"off\"/>\r\n");
      out.write("                        <input type=\"hidden\" name=\"loginpwd\" id=\"loginpwd\" value=\"\" class=\"hide\" />\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"i-pass ico\"></div>\r\n");
      out.write("                        <label id=\"loginpwd_succeed\" class=\"blank invisible\"></label>\r\n");
      out.write("                        <label id=\"loginpwd_error\" class=\"hide\"></label>\r\n");
      out.write("                        <script type=\"text/javascript\">\r\n");
      out.write("\t\t\t\t\t\t\t$('#nloginpwd')[0].onkeypress = function(event){\r\n");
      out.write("\t\t\t\t\t\t\t\tvar e = event||window.event,\r\n");
      out.write("\t\t\t\t\t\t\t\t$tip = $('#capslock'),\r\n");
      out.write("\t\t\t\t\t\t\t\tkc  =  e.keyCode||e.which, // 按键的keyCode\r\n");
      out.write("\t\t\t\t\t\t\t\tisShift  =  e.shiftKey ||(kc  ==   16 ) || false ; // shift键是否按住\r\n");
      out.write("\t\t\t\t\t\t\t\tif (((kc >=65&&kc<=90)&&!isShift)|| ((kc >=97&&kc<=122)&&isShift)){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$tip.show();\r\n");
      out.write("\t\t\t\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$tip.hide();\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t};\r\n");
      out.write("                        </script>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <input type=\"hidden\" name=\"machineNet\" id=\"machineNet\" value=\"\" class=\"hide\"/>\r\n");
      out.write("                <input type=\"hidden\" name=\"machineCpu\" id=\"machineCpu\" value=\"\" class=\"hide\"/>\r\n");
      out.write("                <input type=\"hidden\" name=\"machineDisk\" id=\"machineDisk\" value=\"\" class=\"hide\"/>\r\n");
      out.write("                \r\n");
      out.write("                <div class=\"item fore3  hide \" id=\"o-authcode\">\r\n");
      out.write("                    <span>验证码</span>\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"item-ifo\">\r\n");
      out.write("                        <input type=\"text\" id=\"authcode\" class=\"text text-1\" name=\"authcode\" tabindex=\"6\" style=\"ime-mode:disabled\"/>\r\n");
      out.write("                        <label class=\"img\">\r\n");
      out.write("                            <img style=\"cursor:pointer;width:100px;height:33px;display:block;\"\r\n");
      out.write("                                 src2=\"https://authcode.jd.com/verify/image?a=1&amp;acid=1359c13d-7daa-4a2a-972d-f09e09e6605a&amp;uid=1359c13d-7daa-4a2a-972d-f09e09e6605a\"\r\n");
      out.write("                                                                 onclick=\"this.src= document.location.protocol +'//authcode.jd.com/verify/image?a=1&amp;acid=1359c13d-7daa-4a2a-972d-f09e09e6605a&amp;uid=1359c13d-7daa-4a2a-972d-f09e09e6605a&amp;yys='+new Date().getTime();$('#authcode').val('');\"\r\n");
      out.write("                                 ver_colorofnoisepoint=\"#888888\" id=\"JD_Verification1\">\r\n");
      out.write("                        </label>\r\n");
      out.write("                        <label class=\"ftx23 hline\">看不清？<br><a href=\"javascript:void(0)\" class=\"flk13\"\r\n");
      out.write("                                                              onclick=\"jQuery('#JD_Verification1').click();\">换一张</a></label>\r\n");
      out.write("                        <label id=\"authcode_succeed\" class=\"blank invisible\"></label>\r\n");
      out.write("                        <label id=\"authcode_error\" class=\"hide\"></label>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"item fore4 hide\" id=\"autoentry\">\r\n");
      out.write("                    <div class=\"item-ifo\">\r\n");
      out.write("                        <input type=\"checkbox\" class=\"checkbox\" name=\"chkRememberMe\" clstag=\"passport|keycount|login|04\"/>\r\n");
      out.write("                        <label class=\"mar\">自动登录</label>\r\n");
      out.write("                                                <div style=\"float:left;\" id=\"ctrlDiv\">\r\n");
      out.write("                            <input type=\"checkbox\" class=\"checkbox\" id=\"chkOpenCtrl\" name=\"chkOpenCtrl\" onclick=\"javascript:inputSelect();\"/>\r\n");
      out.write("                            <label class=\"mar\" id=\"jdsafe\">安全控件登录<div class=\"tip-safe\" style=\"display:none;\" id=\"tip-safe\">安全控件可提高账户安全性，加密保护您的密码。</div></label>\r\n");
      out.write("                        </div>\r\n");
      out.write("                                                <label><a href=\"http://safe.jd.com/findPwd/index.action\" class=\"\" clstag=\"passport|keycount|login|05\">忘记密码?</a></label>\r\n");
      out.write("                        <div class=\"clr\"></div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"updata\" id=\"updata\" style=\"display:none;\">安全控件升级了!<a href=\"javascript:void(0);\" onclick=\"updateCtl()\" class=\"up-two\"></a>立即更新</a> <a class=\"up-one\" href=\"javascript:void(0);\" onclick=\"$('#updata').hide();\"></a></div>\r\n");
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"item login-btn2013\">\r\n");
      out.write("                    <input type=\"button\" class=\"btn-img btn-entry\" id=\"loginsubmit\" value=\"登录\" tabindex=\"8\" clstag=\"passport|keycount|login|06\"/>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("                <div class=\"coagent hide\" clstag=\"passport|keycount|login|07\">\r\n");
      out.write("                    <label class=\"ftx24\">\r\n");
      out.write("                        使用合作网站账号登录京淘：\r\n");
      out.write("                        <span class=\"clr\"></span><input type=\"hidden\" name=\"KbmPxRtWsz\" value=\"IMdug\" />\r\n");
      out.write("                    <span class=\"btns qq\"><s></s> <a href=\"javascript:void(0)\"\r\n");
      out.write("                                                     onclick=\"window.location='http://qq.jd.com/new/qq/login.aspx'+window.location.search;return false;\">QQ</a></span>\r\n");
      out.write("                        <dl class=\"btns more-slide\">\r\n");
      out.write("                            <dt><b>其它</b></dt>\r\n");
      out.write("                            <dd>\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"javascript:void(0)\"\r\n");
      out.write("                                               onclick=\"window.location='http://qq.jd.com/new/netease/login.action'+window.location.search;return false;\">网易</a>\t\t\t   \r\n");
      out.write("\t\t\t\t\t\t\t\t<a style=\"margin-left:30px;\" href=\"javascript:void(0)\"\r\n");
      out.write("                                               onclick=\"window.location='http://qq.jd.com/new/renren/login.action'+window.location.search;return false;\">人人</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"javascript:void(0)\"\r\n");
      out.write("                                               onclick=\"window.location='http://qq.jd.com/new/douban/login.action'+window.location.search;return false;\">豆瓣</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<a style=\"margin-left:30px;\" href=\"javascript:void(0)\"\r\n");
      out.write("                                               onclick=\"window.location='http://qq.jd.com/new/sohu/login.action'+window.location.search;return false;\">搜狐</a>\r\n");
      out.write("                                <br>\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"javascript:void(0)\"\r\n");
      out.write("                                               onclick=\"window.location='http://qq.jd.com/new/kaixin001/login.action'+window.location.search;return false;\">开心</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<a style=\"margin-left:30px;\" href=\"javascript:void(0)\"\r\n");
      out.write("                                               onclick=\"window.location='http://qq.jd.com/new/alipay/login.aspx'+window.location.search;return false;\">支付宝</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<br>\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"javascript:void(0)\"\r\n");
      out.write("                                               onclick=\"window.location='http://qq.jd.com/new/qihao/login.action'+window.location.search;return false;\">奇虎360</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<a style=\"margin-left:10px;\" href=\"javascript:void(0)\"\r\n");
      out.write("                                               onclick=\"window.location='http://qq.jd.com/new/sina/login.action'+window.location.search;return false;\">新浪微博</a>\r\n");
      out.write("                            </dd>\r\n");
      out.write("                        </dl>\r\n");
      out.write("                        <a id=\"kx001_btn_login\" style=\"display:none\"></a>\r\n");
      out.write("                    </label>\r\n");
      out.write("                </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"free-regist\">\r\n");
      out.write("            <span><a href=\"http://www.jt.com/user/register.html\" clstag=\"passport|keycount|login|08\">免费注册&gt;&gt;</a></span>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</form>\r\n");
      out.write("<div class=\"w1\">\r\n");
      out.write("    <div id=\"mb-bg\" class=\"mb\"></div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"w\">\r\n");
      out.write("\t<!-- links start -->\r\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../commons/footer-links.jsp", out, false);
      out.write("<!-- links end -->\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/login/login.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/login/jdThickBox.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/login/checkClient.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("   $(\"#jdsafe\").hover(function () {\r\n");
      out.write("       $(\"#tip-safe\").show();\r\n");
      out.write("   }, function () {\r\n");
      out.write("       $(\"#tip-safe\").hide();\r\n");
      out.write("   });\r\n");
      out.write("   $('.more-slide').bind('mouseenter mouseleave', function() {\r\n");
      out.write("       $(this).toggleClass('hover');\r\n");
      out.write("   });\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
