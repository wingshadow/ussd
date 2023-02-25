// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Config.java

package ljwf.ctr;

import java.util.HashMap;

public class Config
{

	public static HashMap m = null;

	public Config()
	{
	}

	public static void init()
	{
		m = new HashMap();
		m.put("addrmain", new String[] {
			"com.inspur.addr.cmd.addrmain"
		});
		m.put("treemain", new String[] {
			"/jsp/tree/treemain.jsp"
		});
		m.put("LeftTree", new String[] {
			"com.inspur.tree.LeftTree", "/jsp/tree/left.jsp"
		});
		m.put("mainM", new String[] {
			"/default.jsp"
		});
		m.put("toLogin", new String[] {
			"/menhu.jsp"
		});
		m.put("login", new String[] {
			"com.inspur.login.cmd.Login", "/default.jsp"
		});
		m.put("logout", new String[] {
			"com.inspur.login.cmd.Logout", "/menhu.jsp"
		});
		m.put("backuplog", new String[] {
			"com.inspur.maslog.cmd.BackUpLog", ""
		});
		m.put("delMaslogs", new String[] {
			"com.inspur.maslog.cmd.DelMasLogs", "com.inspur.maslog.cmd.CmdSearchLog", "/jsp/maslog/maslog.jsp"
		});
		m.put("orgmain", new String[] {
			"com.inspur.org.cmd.OrgMain"
		});
		m.put("ViewAddOrg", new String[] {
			"com.inspur.org.cmd.ViewAddOrg", "/jsp/org/org.jsp"
		});
		m.put("ViewUpdateOrg", new String[] {
			"com.inspur.org.cmd.ViewUpdateOrg", "/jsp/org/org.jsp"
		});
		m.put("CmdAddOrg", new String[] {
			"com.inspur.org.cmd.CmdAddOrg"
		});
		m.put("CmdUpdateOrg", new String[] {
			"com.inspur.org.cmd.CmdUpdateOrg"
		});
		m.put("CmdSearchOrg", new String[] {
			"com.inspur.org.cmd.CmdSearchOrg", "/jsp/org/orglist.jsp"
		});
		m.put("CmdDelOrg", new String[] {
			"com.inspur.org.cmd.CmdDelOrg"
		});
		m.put("ViewUpOrgSn", new String[] {
			"com.inspur.org.cmd.ViewUpOrgSn", "/jsp/org/uporgsn.jsp"
		});
		m.put("CmdUpOrgSn", new String[] {
			"com.inspur.org.cmd.CmdUpOrgSn"
		});
		m.put("resetpwd", new String[] {
			"com.inspur.user.cmd.ResetPwd", "/jsp/user/resetpwd.jsp"
		});
		m.put("usermain", new String[] {
			"com.inspur.user.cmd.UserMain"
		});
		m.put("upexcel", new String[] {
			"com.inspur.apuser.cmd.UploadExcel", "/jsp/apuser/up.jsp"
		});
		m.put("appusertmp", new String[] {
			"com.inspur.apuser.cmd.AppuserCmd", "/jsp/apuser/appusertmp.jsp"
		});
		m.put("delappusertmp", new String[] {
			"com.inspur.apuser.cmd.DelAppUserTmp"
		});
		m.put("appusertmpedit", new String[] {
			"com.inspur.apuser.cmd.AppUserTmpEdit", "/jsp/apuser/appusertmpedit.jsp"
		});
		m.put("editAppUserTmpCommit", new String[] {
			"com.inspur.apuser.cmd.EditAppUserTmpCommit"
		});
		m.put("CmdSearchUser", new String[] {
			"com.inspur.user.cmd.CmdSearchUser", "/jsp/user/userlist.jsp"
		});
		m.put("CmdAddrSearchUser", new String[] {
			"com.inspur.user.cmd.CmdSearchUser", "/jsp/user/addrsearchuser.jsp"
		});
		m.put("ViewAddUser", new String[] {
			"com.inspur.user.cmd.ViewAddUser", "/jsp/user/user.jsp"
		});
		m.put("ViewUpdateUser", new String[] {
			"com.inspur.user.cmd.ViewUpdateUser", "/jsp/user/user.jsp"
		});
		m.put("ViewShowUser", new String[] {
			"com.inspur.user.cmd.ViewShowUser", "/jsp/user/showuser.jsp"
		});
		m.put("CmdAddUser", new String[] {
			"com.inspur.user.cmd.CmdAddUser"
		});
		m.put("CmdUpdateUser", new String[] {
			"com.inspur.user.cmd.CmdUpdateUser"
		});
		m.put("CmdDelUser", new String[] {
			"com.inspur.user.cmd.CmdDelUser"
		});
		m.put("CmdUpdatePw", new String[] {
			"com.inspur.user.cmd.CmdUpdatePw", "/jsp/user/viewpassword.jsp"
		});
		m.put("ViewUpdatepw", new String[] {
			"/jsp/user/viewpassword.jsp"
		});
		m.put("UserAddRole", new String[] {
			"com.inspur.user.cmd.UserAddRole"
		});
		m.put("getrolelist", new String[] {
			"com.inspur.user.cmd.ViewSetRole", "/jsp/user/getrolelist.jsp"
		});
		m.put("CmdSelectUser", new String[] {
			"com.inspur.user.cmd.CmdSelectUser", "/jsp/tree/getuser.jsp"
		});
		m.put("PutExcel", new String[] {
			"com.inspur.user.cmd.PutExcel"
		});
		m.put("ImportUserFromExcel", new String[] {
			"com.inspur.user.cmd.ImportUserFromExcel", "/jsp/user/importexcel.jsp"
		});
		m.put("ViewUpUserSn", new String[] {
			"com.inspur.user.cmd.ViewUpUserSn", "/jsp/user/upusersn.jsp"
		});
		m.put("CmdUpUserSn", new String[] {
			"com.inspur.user.cmd.CmdUpUserSn"
		});
		m.put("CmdSearchZD", new String[] {
			"com.inspur.addr.cmd.CmdSearchZD", "/jsp/addr/zdlist.jsp"
		});
		m.put("ViewAddZD", new String[] {
			"/jsp/addr/zd.jsp"
		});
		m.put("CmdAddZD", new String[] {
			"com.inspur.addr.cmd.CmdAddZD", "/jsp/addr/zd.jsp"
		});
		m.put("ViewUpZD", new String[] {
			"com.inspur.addr.cmd.ViewUpZD", "/jsp/addr/zd.jsp"
		});
		m.put("CmdUpZD", new String[] {
			"com.inspur.addr.cmd.CmdUpZD", "/jsp/addr/zd.jsp"
		});
		m.put("CmdDelZD", new String[] {
			"com.inspur.addr.cmd.CmdDelZD"
		});
		m.put("CmdSearchGandM", new String[] {
			"com.inspur.addr.cmd.CmdSearchGandM", "/jsp/addr/GandMlist.jsp"
		});
		m.put("CmdAddGandMD", new String[] {
			"com.inspur.addr.cmd.CmdAddGandM"
		});
		m.put("CmdDelGandM", new String[] {
			"com.inspur.addr.cmd.CmdDelGandM"
		});
		m.put("AddrTree", new String[] {
			"com.inspur.addr.cmd.AddrTree", "/jsp/addr/addrtree.jsp"
		});
		m.put("ViewShowAddr", new String[] {
			"com.inspur.addr.cmd.ViewShowAddr", "/jsp/addr/showaddr.jsp"
		});
		m.put("ViewAddAddr", new String[] {
			"/jsp/addr/addr.jsp"
		});
		m.put("ViewAddGroup", new String[] {
			"/jsp/addr/group.jsp"
		});
		m.put("ViewUpdateAddr", new String[] {
			"com.inspur.addr.cmd.ViewUpdateAddr", "/jsp/addr/addr.jsp"
		});
		m.put("ViewUpdateGroup", new String[] {
			"com.inspur.addr.cmd.ViewUpdateGroup", "/jsp/addr/group.jsp"
		});
		m.put("CmdAddAddr", new String[] {
			"com.inspur.addr.cmd.CmdAddAddr"
		});
		m.put("CmdUpdateAddr", new String[] {
			"com.inspur.addr.cmd.CmdUpdateAddr"
		});
		m.put("CmdSearchAddr", new String[] {
			"com.inspur.addr.cmd.CmdSearchAddr", "/jsp/addr/addrlist.jsp"
		});
		m.put("CmdSelectAddr", new String[] {
			"com.inspur.addr.cmd.CmdSelectAddr", "/jsp/tree/getuser.jsp"
		});
		m.put("CmdDelAddr", new String[] {
			"com.inspur.addr.cmd.CmdDelAddr"
		});
		m.put("AddrPutExcel", new String[] {
			"com.inspur.addr.cmd.AddrPutExcel"
		});
		m.put("ImporttxlFromExcel", new String[] {
			"com.inspur.addr.cmd.ImporttxlFromExcel", "/jsp/addr/importexcel.jsp"
		});
		m.put("seluser", new String[] {
			"com.inspur.addr.cmd.SelectUsrCmd"
		});
		m.put("roleList", new String[] {
			"com.inspur.system.role.cmd.QueryRole", "/jsp/system/role/role_list.jsp"
		});
		m.put("forAddRole", new String[] {
			"/jsp/system/role/forAddRole.jsp"
		});
		m.put("addRole", new String[] {
			"com.inspur.system.role.cmd.AddRole"
		});
		m.put("forModifyRole", new String[] {
			"com.inspur.system.role.cmd.ForModifyRole", "/jsp/system/role/forModifyRole.jsp"
		});
		m.put("modifyRole", new String[] {
			"com.inspur.system.role.cmd.ModifyRole"
		});
		m.put("delRole", new String[] {
			"com.inspur.system.role.cmd.DeleteRole"
		});
		m.put("getMenuByRoleId", new String[] {
			"com.inspur.system.role.cmd.MenuTreeData", "/jsp/system/role/divTree.jsp"
		});
		m.put("addMenuRole", new String[] {
			"com.inspur.system.role.cmd.AddMenuRole"
		});
		m.put("funTree", new String[] {
			"/jsp/funcation/Left.jsp"
		});
		m.put("fun", new String[] {
			"/jsp/funcation/funmain.jsp"
		});
		m.put("funMenuList", new String[] {
			"com.inspur.kjava.menu.cmd.FunMenuList", "/jsp/funcation/funlist.jsp"
		});
		m.put("funMenuDell", new String[] {
			"com.inspur.kjava.menu.cmd.FunMenuDell"
		});
		m.put("errorPageFun", new String[] {
			"/jsp/funcation/errorpage.jsp"
		});
		m.put("funUpdate", new String[] {
			"com.inspur.kjava.menu.cmd.FunMenuUpdateBefore"
		});
		m.put("updateFun", new String[] {
			"com.inspur.kjava.menu.cmd.UpdateFunBefore", "/jsp/funcation/updatefun.jsp"
		});
		m.put("funcationUpdate", new String[] {
			"com.inspur.kjava.menu.cmd.FuncationUpdate", "/jsp/funcation/funlist.jsp"
		});
		m.put("updateFunNew", new String[] {
			"com.inspur.kjava.menu.cmd.UpdateFunNew", "/jsp/funcation/funmenuupdate.jsp"
		});
		m.put("updatePara", new String[] {
			"com.inspur.kjava.menu.cmd.UpdatePara", "/jsp/funcation/updatepara.jsp"
		});
		m.put("updateParaOK", new String[] {
			"com.inspur.kjava.menu.cmd.UpdateParaOK", "/jsp/funcation/funmenuupdate.jsp"
		});
		m.put("updateParaAddPara", new String[] {
			"com.inspur.kjava.menu.cmd.UpdateParaAddPara", "/jsp/funcation/funmenuupdate.jsp"
		});
		m.put("dellPara", new String[] {
			"com.inspur.kjava.menu.cmd.DellPara", "/jsp/funcation/funmenuupdate.jsp"
		});
		m.put("updateMenu", new String[] {
			"com.inspur.kjava.menu.cmd.UpdateMenuBefore", "/jsp/funcation/updatemenu.jsp"
		});
		m.put("updateMenuOK", new String[] {
			"com.inspur.kjava.menu.cmd.UpdateMenu", "/jsp/funcation/funlist.jsp"
		});
		m.put("menuAddD", new String[] {
			"com.inspur.kjava.menu.cmd.MenuAddBefore", "/jsp/funcation/menuadd.jsp"
		});
		m.put("menuAddOK", new String[] {
			"com.inspur.kjava.menu.cmd.MenuAdd"
		});
		m.put("funAddTest", new String[] {
			"com.inspur.kjava.menu.cmd.FuncationAddFirst", "/jsp/funcation/funmenuadd.jsp"
		});
		m.put("funcationAdd", new String[] {
			"com.inspur.kjava.menu.cmd.FuncationAdd"
		});
		m.put("functionAdd", new String[] {
			"com.inspur.kjava.menu.cmd.FunctionAdd", "/jsp/funcation/funmenuadd.jsp"
		});
		m.put("funVisitMap", new String[] {
			"com.inspur.kjava.servlet.map.FunVisitMap", "/jsp/funcation/map/funvisitmap.jsp"
		});
		m.put("smsTree", new String[] {
			"/jsp/sms/Left.jsp"
		});
		m.put("sms", new String[] {
			"/jsp/sms/smsmain.jsp"
		});
		m.put("smsUpdate", new String[] {
			"com.inspur.sms.SmsMenuUpdateBefore", "/jsp/sms/smsupdate.jsp"
		});
		m.put("smsUpdateOK", new String[] {
			"com.inspur.sms.SmsMenuUpdate", "/jsp/sms/smslist.jsp"
		});
		m.put("smsMenuList", new String[] {
			"com.inspur.sms.SmsMenuList", "/jsp/sms/smslist.jsp"
		});
		m.put("smsMenuAdd", new String[] {
			"/jsp/sms/smsmenuadd.jsp"
		});
		m.put("smsMenuAddOK", new String[] {
			"com.inspur.sms.SmsMenuAdd"
		});
		m.put("smsMenuDell", new String[] {
			"com.inspur.sms.SmsMenuDell"
		});
		m.put("errorPageSMS", new String[] {
			"/jsp/sms/errorpage.jsp"
		});
		m.put("USSDControl", new String[] {
			"com.inspur.ussd.cmd.USSDControl", "/jsp/ussd/ussdInfo.jsp"
		});
		m.put("ussdTree", new String[] {
			"/jsp/ussd/Left.jsp"
		});
		m.put("saveussdcontent", new String[] {
			"com.inspur.ussd.cmd.SaveUSSDContent"
		});
		m.put("ussdcontentdel", new String[] {
			"com.inspur.ussd.cmd.USSDContentDel"
		});
		m.put("openussdcontent", new String[] {
			"com.inspur.ussd.cmd.OpenUssdContent", "/jsp/ussd/updateussdcontent.jsp"
		});
		m.put("addussdcontent", new String[] {
			"com.inspur.ussd.cmd.USSDContentAdd", "/jsp/ussd/newussdcontent.jsp"
		});
		m.put("ussdcontenttree", new String[] {
			"/jsp/ussd/ussdcontentLeft.jsp"
		});
		m.put("ussdcontentlist", new String[] {
			"com.inspur.ussd.cmd.USSDContentList", "/jsp/ussd/ussdcontentlist.jsp"
		});
		m.put("ussd", new String[] {
			"/jsp/ussd/ussdmain.jsp"
		});
		m.put("ussdcontent", new String[] {
			"/jsp/ussd/ussdcontent.jsp"
		});
		m.put("ussdUpdate", new String[] {
			"com.inspur.ussd.cmd.UssdMenuUpdateBefore", "/jsp/ussd/ussdupdate.jsp"
		});
		m.put("ussdUpdateOK", new String[] {
			"com.inspur.ussd.cmd.UssdMenuUpdate"
		});
		m.put("ussdMenuList", new String[] {
			"com.inspur.ussd.cmd.UssdMenuList", "/jsp/ussd/ussdlist.jsp"
		});
		m.put("ussdMenuAdd", new String[] {
			"/jsp/ussd/ussdmenuadd.jsp"
		});
		m.put("ussdMenuAddOK", new String[] {
			"com.inspur.ussd.cmd.UssdMenuAdd"
		});
		m.put("ussdMenuDell", new String[] {
			"com.inspur.ussd.cmd.UssdMenuDell"
		});
		m.put("errorPage", new String[] {
			"/jsp/ussd/errorpage.jsp"
		});
		m.put("jsUssdUpdate", new String[] {
			"com.inspur.ussd.cmd.UssdMenuUpdateByJS"
		});
		m.put("mobileTree", new String[] {
			"/jsp/kjavamobile/Left.jsp"
		});
		m.put("mobile", new String[] {
			"/jsp/kjavamobile/mobilemain.jsp"
		});
		m.put("mobileUpdateBefore", new String[] {
			"com.inspur.kjavamobile.cmd.MenuUpdateBefore"
		});
		m.put("mobileUpdate", new String[] {
			"com.inspur.kjavamobile.cmd.MobileMenuUpdateBefore", "/jsp/kjavamobile/mobileupdate.jsp"
		});
		m.put("mobileUpdateOK", new String[] {
			"com.inspur.kjavamobile.cmd.MobileMenuUpdate", "/jsp/kjavamobile/mobilelist.jsp"
		});
		m.put("mobileMenuList", new String[] {
			"com.inspur.kjavamobile.cmd.MobileMenuList", "/jsp/kjavamobile/mobilelist.jsp"
		});
		m.put("mobileMenuAdd", new String[] {
			"/jsp/kjavamobile/mobilemenuadd.jsp"
		});
		m.put("mobileMenuAddOK", new String[] {
			"com.inspur.kjavamobile.cmd.MobileMenuAdd"
		});
		m.put("mobileMenuDell", new String[] {
			"com.inspur.kjavamobile.cmd.MobileMenuDell"
		});
		m.put("errorPage", new String[] {
			"/jsp/kjavamobile/errorpage.jsp"
		});
		m.put("menuAdd", new String[] {
			"/jsp/kjavamobile/menuadd.jsp"
		});
		m.put("menuAddOKK", new String[] {
			"com.inspur.kjavamobile.cmd.MenuAdd"
		});
		m.put("menuUpdate", new String[] {
			"com.inspur.kjavamobile.cmd.MenuUpdateFirst", "/jsp/kjavamobile/menuupdate.jsp"
		});
		m.put("menuUpdateOK", new String[] {
			"com.inspur.kjavamobile.cmd.MenuUpdate", "/jsp/kjavamobile/mobilelist.jsp"
		});
		m.put("mobileQuery", new String[] {
			"/jsp/kjavamobile/query/queryMain.jsp"
		});
		m.put("mobileQueryOK", new String[] {
			"com.inspur.kjavamobile.query.MobileQueryOK", "/jsp/kjavamobile/query/mobileQueryList.jsp"
		});
		m.put("mobileQueryShow", new String[] {
			"com.inspur.kjavamobile.query.MobileMenuUpdateBefore", "/jsp/kjavamobile/query/mobileupdate.jsp"
		});
		m.put("fileUpload", new String[] {
			"/jsp/kfileupload/fileupload.jsp"
		});
		m.put("fileUploadOK", new String[] {
			"com.inspur.kfileupload.cmd.FileUploadOK", "/jsp/kfileupload/filequery.jsp"
		});
		m.put("mobileCheck", new String[] {
			"com.inspur.kfileupload.cmd.MobileCheck", "/jsp/kfileupload/mobileCheck.jsp"
		});
		m.put("mobileTreeFile", new String[] {
			"/jsp/kfileupload/fileLeft.jsp"
		});
		m.put("mobileFile", new String[] {
			"/jsp/kfileupload/mobilemainfile.jsp"
		});
		m.put("mobileMenuListFile", new String[] {
			"com.inspur.kfileupload.cmd.MobileMenuListFile", "/jsp/kfileupload/mobileCheck.jsp"
		});
		m.put("fileQuery", new String[] {
			"com.inspur.kfileupload.cmd.FileQuery", "/jsp/kfileupload/filequery.jsp"
		});
		m.put("fileUpdate", new String[] {
			"com.inspur.kfileupload.cmd.FileUpdate", "/jsp/kfileupload/fileUpdateMain.jsp"
		});
		m.put("fileUpdateOK", new String[] {
			"com.inspur.kfileupload.cmd.FileUpdateOK", "/jsp/kfileupload/filequery.jsp"
		});
		m.put("inselUpdate", new String[] {
			"com.inspur.kfileupload.cmd.InselUpdate", "/jsp/kfileupload/inselUpdate.jsp"
		});
		m.put("inselUpdateOK", new String[] {
			"com.inspur.kfileupload.cmd.InselUpdateOK"
		});
		m.put("fileDelete", new String[] {
			"com.inspur.kfileupload.cmd.FileDelete"
		});
		m.put("fileQueryBegin", new String[] {
			"/jsp/kfileupload/query/queryMain.jsp"
		});
		m.put("fileQueryOK", new String[] {
			"com.inspur.kfileupload.query.FileQueryOK", "/jsp/kfileupload/query/fileQueryList.jsp"
		});
		m.put("fileQueryShow", new String[] {
			"com.inspur.kfileupload.query.FileMenuUpdateBefore", "/jsp/kfileupload/query/fileupdate.jsp"
		});
		m.put("fileUploadNew", new String[] {
			"com.inspur.kfileupload.cmd.FileUploadNew", "/jsp/kfileupload/fileuploadnew.jsp"
		});
		m.put("fileUploadNewOK", new String[] {
			"com.inspur.kfileupload.cmd.FileUploadNewOK", "/jsp/kfileupload/filequery.jsp"
		});
		m.put("fileVersionUpdate", new String[] {
			"com.inspur.kfileupload.cmd.ModVersion", "/jsp/kfileupload/modVersion.jsp"
		});
		m.put("fileVersionUpdateOK", new String[] {
			"com.inspur.kfileupload.cmd.ModVersionOK", "/jsp/kfileupload/filequery.jsp"
		});
		m.put("viewtag", new String[] {
			"com.inspur.tagmanage.cmd.ViewDefinedTag", "/jsp/viewtag/viewtag.jsp"
		});
		m.put("getdefimglinktag", new String[] {
			"/jsp/jscripts/tiny_mce/plugins/defimglinktag/defimglinktag.jsp"
		});
		m.put("getchanneltag", new String[] {
			"com.inspur.template.cmd.GetChannelTagCmd", "/jsp/jscripts/tiny_mce/plugins/deftag/channeltag.jsp"
		});
		m.put("getlmtag", new String[] {
			"com.inspur.template.cmd.GetLmTagCmd", "/jsp/jscripts/tiny_mce/plugins/lmtag/lmtag.jsp"
		});
		m.put("getwztag", new String[] {
			"/jsp/jscripts/tiny_mce/plugins/wztag/wztag.jsp"
		});
		m.put("gettitag", new String[] {
			"com.inspur.template.cmd.GetTiTagCmd", "/jsp/jscripts/tiny_mce/plugins/titag/titag.jsp"
		});
		m.put("getactag", new String[] {
			"com.inspur.template.cmd.GetContentTagCmd", "/jsp/jscripts/tiny_mce/plugins/actag/actag.jsp"
		});
		m.put("getdefimgtag", new String[] {
			"com.inspur.template.cmd.GetDefImgTag", "/jsp/jscripts/tiny_mce/plugins/defimgtag/defimgtag.jsp"
		});
		m.put("getdefurltag", new String[] {
			"/jsp/jscripts/tiny_mce/plugins/def_url_tag/defimgtag.jsp"
		});
		m.put("selectimg", new String[] {
			"com.inspur.template.cmd.SelectImgCmd", "/jsp/template/selectimg.jsp"
		});
		m.put("getdeflinktag", new String[] {
			"/jsp/jscripts/tiny_mce/plugins/deflinktag/deflinktag.jsp"
		});
		m.put("getdeflinkimgtag", new String[] {
			"/jsp/jscripts/tiny_mce/plugins/deflinkimgtag/deflinktag.jsp"
		});
		m.put("getdefdhtag", new String[] {
			"/jsp/jscripts/tiny_mce/plugins/defdhtag/defdhtag.jsp"
		});
		m.put("getpdtag", new String[] {
			"/jsp/jscripts/tiny_mce/plugins/defpdtag/deflinktag.jsp"
		});
		m.put("getpagetag", new String[] {
			"/jsp/jscripts/tiny_mce/plugins/defpagetag/defpagetag.jsp"
		});
		m.put("getsytag", new String[] {
			"/jsp/jscripts/tiny_mce/plugins/defsytag/deflinktag.jsp"
		});
		m.put("getdefsetag", new String[] {
			"com.inspur.template.cmd.GetDefServiceTag", "/jsp/jscripts/tiny_mce/plugins/defsetag/defsetag.jsp"
		});
		m.put("getdefhrtag", new String[] {
			"/jsp/jscripts/tiny_mce/plugins/defhrtag/defhrtag.jsp"
		});
		m.put("gettemplatelist", new String[] {
			"com.inspur.template.cmd.GetTemplateListCmd", "/jsp/template/templatelist.jsp"
		});
		m.put("newtemplate", new String[] {
			"com.inspur.template.cmd.CreateNewTemplateCmd", "/jsp/template/templatenew.jsp"
		});
		m.put("savetemplate", new String[] {
			"com.inspur.template.cmd.SaveTemplateCmd", "/jsp/template/templatelist.jsp"
		});
		m.put("deltemplate", new String[] {
			"com.inspur.template.cmd.DeleteTemplateCmd", "/jsp/template/templatelist.jsp"
		});
		m.put("opentemplate", new String[] {
			"com.inspur.template.cmd.OpenTemplateCmd", "/jsp/template/templatemod.jsp"
		});
		m.put("getpicturelist", new String[] {
			"com.inspur.template.cmd.GetPictureListCmd", "/jsp/template/picturelist.jsp"
		});
		m.put("newpicture", new String[] {
			"com.inspur.template.cmd.CreateNewPictureCmd", "/jsp/template/picturenew.jsp"
		});
		m.put("savepicture", new String[] {
			"com.inspur.template.cmd.SavePictureCmd", "/jsp/template/picturelist.jsp"
		});
		m.put("deletepicture", new String[] {
			"com.inspur.template.cmd.DeletePictureCmd", "/jsp/template/picturelist.jsp"
		});
		m.put("getchannellist", new String[] {
			"com.inspur.channel.cmd.GetChannelListCmd", "/jsp/channel/channellist.jsp"
		});
		m.put("openchannel", new String[] {
			"com.inspur.channel.cmd.OpenChannelCmd", "/jsp/channel/updatechannel.jsp"
		});
		m.put("savechannel", new String[] {
			"com.inspur.channel.cmd.SaveChannelCmd", "/jsp/channel/channellist.jsp"
		});
		m.put("updatechannel", new String[] {
			"com.inspur.channel.cmd.UpdateChannelCmd", "/jsp/channel/channellist.jsp"
		});
		m.put("delchannel", new String[] {
			"com.inspur.channel.cmd.DeleteChannelCmd", "/jsp/channel/channellist.jsp"
		});
		m.put("newchannel", new String[] {
			"/jsp/channel/newchannel.jsp"
		});
		m.put("columnmain", new String[] {
			"/jsp/column/columnmain.jsp"
		});
		m.put("channeltree", new String[] {
			"/jsp/column/channeltree.jsp"
		});
		m.put("getcolumnlist", new String[] {
			"com.inspur.column.cmd.GetColumnListCmd", "/jsp/column/columnlist.jsp"
		});
		m.put("newcolumn", new String[] {
			"com.inspur.column.cmd.NewColumnCmd", "/jsp/column/newcolumn.jsp"
		});
		m.put("savecolumn", new String[] {
			"com.inspur.column.cmd.SaveColumnCmd"
		});
		m.put("updatecolumn", new String[] {
			"com.inspur.column.cmd.UpdateColumnCmd"
		});
		m.put("delcolumn", new String[] {
			"com.inspur.column.cmd.DeleteColumnlCmd"
		});
		m.put("opencolumn", new String[] {
			"com.inspur.column.cmd.OpenColumnCmd", "/jsp/column/updatecolumn.jsp"
		});
		m.put("articlemain", new String[] {
			"/jsp/article/articlemain.jsp"
		});
		m.put("achanneltree", new String[] {
			"/jsp/article/channeltree.jsp"
		});
		m.put("getarticlelist", new String[] {
			"com.inspur.article.cmd.GetArticleListCmd", "/jsp/article/articlelist.jsp"
		});
		m.put("newarticle", new String[] {
			"com.inspur.article.cmd.NewArticleCmd", "/jsp/article/newarticle.jsp"
		});
		m.put("imparticle", new String[] {
			"com.inspur.article.cmd.ImpArticleCmd", "/jsp/article/imparticle.jsp"
		});
		m.put("savearticle", new String[] {
			"com.inspur.article.cmd.SaveArticleCmd"
		});
		m.put("saveimparticle", new String[] {
			"com.inspur.article.cmd.SaveImpArticleCmd"
		});
		m.put("updatearticle", new String[] {
			"com.inspur.article.cmd.UpdateArticleCmd"
		});
		m.put("delarticle", new String[] {
			"com.inspur.article.cmd.DeleteArticleCmd"
		});
		m.put("openarticle", new String[] {
			"com.inspur.article.cmd.OpenArticleCmd", "/jsp/article/updatearticle.jsp"
		});
		m.put("wapTree", new String[] {
			"/jsp/service/Left.jsp"
		});
		m.put("wap", new String[] {
			"/jsp/service/wapmain.jsp"
		});
		m.put("wapMenuList", new String[] {
			"com.inspur.service.menu.cmd.WapMenuList", "/jsp/service/waplist.jsp"
		});
		m.put("wapMenuDell", new String[] {
			"com.inspur.service.menu.cmd.WapMenuDell"
		});
		m.put("errorPageFun", new String[] {
			"/jsp/service/errorpage.jsp"
		});
		m.put("wapUpdate", new String[] {
			"com.inspur.service.menu.cmd.WapMenuUpdateBefore"
		});
		m.put("updateWap", new String[] {
			"com.inspur.service.menu.cmd.UpdateWapBefore", "/jsp/service/updateWap.jsp"
		});
		m.put("wapUpdateMenu", new String[] {
			"com.inspur.service.menu.cmd.WapUpdate"
		});
		m.put("updateWapMenu", new String[] {
			"com.inspur.service.menu.cmd.UpdateWapMenuBefore", "/jsp/service/updatewapmenu.jsp"
		});
		m.put("updateWapMenuOK", new String[] {
			"com.inspur.service.menu.cmd.UpdateWapMenu", "/jsp/service/waplist.jsp"
		});
		m.put("wapmenuAdd", new String[] {
			"/jsp/service/menuWapadd.jsp"
		});
		m.put("wapmenuAddOK", new String[] {
			"com.inspur.service.menu.cmd.MenuWapAdd"
		});
		m.put("wapAddTest", new String[] {
			"/jsp/service/wapmenuadd.jsp"
		});
		m.put("wapAdd", new String[] {
			"com.inspur.service.menu.cmd.WapAdd"
		});
		m.put("wapPopupPage", new String[] {
			"/jsp/service/wapPopupPage.jsp"
		});
		m.put("popupwap", new String[] {
			"/jsp/service/popupwapmain.jsp"
		});
		m.put("popupwapTree", new String[] {
			"/jsp/service/popupLeft.jsp"
		});
		m.put("popupwapMenuList", new String[] {
			"com.inspur.service.menu.cmd.WapMenuList", "/jsp/service/popupwaplist.jsp"
		});
		m.put("poputemplist", new String[] {
			"com.inspur.template.cmd.GetTemplateListCmd", "/jsp/template/poputmplist.jsp"
		});
		m.put("publishindex", new String[] {
			"/jsp/publish/publishindex.jsp"
		});
		m.put("publishchanneltree", new String[] {
			"/jsp/publish/channeltree.jsp"
		});
		m.put("pubchannelmain", new String[] {
			"/jsp/publish/pubchannelmain.jsp"
		});
		m.put("getchannel", new String[] {
			"com.inspur.channel.cmd.OpenChannelCmd", "/jsp/publish/publishchannel.jsp"
		});
		m.put("pubchlcolmain", new String[] {
			"/jsp/publish/pubchlcolmain.jsp"
		});
		m.put("getchlcol", new String[] {
			"com.inspur.channel.cmd.OpenChlColCmd", "/jsp/publish/publishchlcol.jsp"
		});
		m.put("publishcolumntree", new String[] {
			"/jsp/publish/columntree.jsp"
		});
		m.put("pubcolumnmain", new String[] {
			"/jsp/publish/pubcolumnmain.jsp"
		});
		m.put("getcolumn", new String[] {
			"com.inspur.column.cmd.OpemPublishColumnCmd", "/jsp/publish/publishcolumn.jsp"
		});
		m.put("publisharticletree", new String[] {
			"/jsp/publish/articletree.jsp"
		});
		m.put("pubarticlenmain", new String[] {
			"/jsp/publish/publisharticlemain.jsp"
		});
		m.put("getarticle", new String[] {
			"com.inspur.article.cmd.GetArticleListCmd", "/jsp/publish/publisharticle.jsp"
		});
		m.put("channelmanagement", new String[] {
			"com.inspur.symbian.cmd.OpenChannelManagement", "/jsp/symbian/openchannel.jsp"
		});
		m.put("symbianarticlemain", new String[] {
			"/jsp/symbian/articlemain.jsp"
		});
		m.put("symbianachanneltree", new String[] {
			"/jsp/symbian/channeltree.jsp"
		});
		m.put("symbiangetarticlelist", new String[] {
			"com.inspur.symbian.cmd.GetArticleListCmd", "/jsp/symbian/articlelist.jsp"
		});
		m.put("symbiannewarticle", new String[] {
			"com.inspur.symbian.cmd.NewArticleCmd", "/jsp/symbian/newarticle.jsp"
		});
		m.put("symbiansavearticle", new String[] {
			"com.inspur.symbian.cmd.SaveArticleCmd"
		});
		m.put("symbianupdatearticle", new String[] {
			"com.inspur.smbarticle.cmd.UpdateArticleCmd"
		});
		m.put("symbiandelarticle", new String[] {
			"com.inspur.symbian.cmd.DeleteArticleCmd"
		});
		m.put("symbianopenarticle", new String[] {
			"com.inspur.symbian.cmd.OpenArticleCmd", "/jsp/symbian/updatearticle.jsp"
		});
		m.put("freechannel", new String[] {
			"com.inspur.scrollsymbian.freechannel.cmd.GetChannelListCmd", "/jsp/scrollsymbian/freechannel/channellist.jsp"
		});
		m.put("openfreechannel", new String[] {
			"com.inspur.scrollsymbian.freechannel.cmd.OpenChannelCmd", "/jsp/scrollsymbian/freechannel/updatechannel.jsp"
		});
		m.put("savefreechannel", new String[] {
			"com.inspur.scrollsymbian.freechannel.cmd.SaveChannelCmd", "/jsp/scrollsymbian/freechannel/channellist.jsp"
		});
		m.put("updatefreechannel", new String[] {
			"com.inspur.scrollsymbian.freechannel.cmd.UpdateChannelCmd", "/jsp/scrollsymbian/freechannel/channellist.jsp"
		});
		m.put("delfreechannel", new String[] {
			"com.inspur.scrollsymbian.freechannel.cmd.DeleteChannelCmd", "/jsp/scrollsymbian/freechannel/channellist.jsp"
		});
		m.put("newfreechannel", new String[] {
			"/jsp/scrollsymbian/freechannel/newchannel.jsp"
		});
		m.put("customfreechannel", new String[] {
			"com.inspur.scrollsymbian.phonecustom.cmd.GetChannelListCmd", "/jsp/scrollsymbian/phonecustom/channellist.jsp"
		});
		m.put("customopenfreechannel", new String[] {
			"com.inspur.scrollsymbian.phonecustom.cmd.OpenChannelCmd", "/jsp/scrollsymbian/phonecustom/updatechannel.jsp"
		});
		m.put("customsavefreechannel", new String[] {
			"com.inspur.scrollsymbian.phonecustom.cmd.SaveChannelCmd", "/jsp/scrollsymbian/phonecustom/channellist.jsp"
		});
		m.put("customupdatefreechannel", new String[] {
			"com.inspur.scrollsymbian.phonecustom.cmd.UpdateChannelCmd", "/jsp/scrollsymbian/phonecustom/channellist.jsp"
		});
		m.put("customdelfreechannel", new String[] {
			"com.inspur.scrollsymbian.phonecustom.cmd.DeleteChannelCmd", "/jsp/scrollsymbian/phonecustom/channellist.jsp"
		});
		m.put("customnewfreechannel", new String[] {
			"com.inspur.scrollsymbian.phonecustom.cmd.NewChannelCmd", "/jsp/scrollsymbian/phonecustom/newchannel.jsp"
		});
		m.put("searchlist", new String[] {
			"com.inspur.searchengine.cmd.SearchCmd", "/jsp/search/searchindex.jsp"
		});
		m.put("deleteindex", new String[] {
			"com.inspur.searchengine.cmd.DeleteIndexCmd", "/jsp/search/searchindex.jsp"
		});
		m.put("searchreslut", new String[] {
			"com.inspur.searchengine.cmd.SearchResultCmd", "/jsp/search/result.jsp"
		});
		m.put("openhref", new String[] {
			"com.inspur.searchengine.cmd.OpenHrefCmd", "/jsp/search/updatehref.jsp"
		});
		m.put("savehref", new String[] {
			"com.inspur.searchengine.cmd.SaveHrefCmd", "/jsp/search/searchindex.jsp"
		});
		m.put("indexs", new String[] {
			"/jsp/search/index.jsp"
		});
		m.put("searchchannellist", new String[] {
			"com.inspur.searchchannel.SearchChannelList", "/jsp/searchchannel/searchlist.jsp"
		});
		m.put("opensearchchannel", new String[] {
			"com.inspur.searchchannel.OpenSearchChannel", "/jsp/searchchannel/searchchannel.jsp"
		});
		m.put("deletesearchchannel", new String[] {
			"com.inspur.searchchannel.DeleteSearchChannel", "/jsp/searchchannel/searchlist.jsp"
		});
		m.put("savesearchchannellist", new String[] {
			"com.inspur.searchchannel.SaveSearchChannel", "/jsp/searchchannel/searchlist.jsp"
		});
		m.put("stopsearch", new String[] {
			"com.inspur.searchchannel.StopChannelSearch", "/jsp/searchchannel/searchlist.jsp"
		});
		m.put("ussdSet", new String[] {
			"com.inspur.ussd.cmd.getUSSDConf", "/jsp/ussd/ussdInfo.jsp"
		});
		m.put("saveUssdInfo", new String[] {
			"com.inspur.ussd.cmd.SaveUssdInfo", "com.inspur.ussd.cmd.getUSSDConf", "/jsp/ussd/ussdInfo.jsp"
		});
		m.put("bosstest", new String[] {
			"com.inspur.bossinterface.TestCmd", "/test.jsp"
		});
	}

	public static String[] get(String n)
	{
		return (String[])m.get(n);
	}

	static 
	{
		init();
	}
}
