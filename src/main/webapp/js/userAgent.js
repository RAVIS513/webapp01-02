/**
 * 端末種別判定
 */
var userAgent = function() {
	this.pc_url = "endlesstrip/";
	this.mobile_url = this.pc_url + "sp/"
}

userAgent.prototype = {

		// UserAgent判別
		find : function() {
			var ua = (function(u){
				var mobile = {
						0: (u.indexOf("windows") != -1 && u.indexOf("phone") != -1)
						|| u.indexOf("iphone") != -1
						|| u.indexOf("ipod") != -1
						|| (u.indexOf("android") != -1 && u.indexOf("mobile") != -1)
						|| (u.indexOf("firefox") != -1 && u.indexOf("mobile") != -1)
						|| u.indexOf("blackberry") != -1,
						iphone: u.indexOf("iphone") != -1,
						android: (u.indexOf("android") != -1 && u.indexOf("mobile") != -1)
				};
				var pc = !mobile[0];
				return {
					Mobile: mobile,
					PC: pc
				};
			})(window.navigator.userAgent.toLowerCase());
			return ua;
		},

		// to Mobile
		toMobile : function() {
			if (this.find().Mobile[0]) {
				var href = window.location.href;
				if (href.indexOf(this.mobile_url) == -1) {
					location.href = href.replace(this.pc_url, this.mobile_url);
				}
			}
		},

		// to PC
		toPC : function() {
			if (this.find().PC) {
				var href = window.location.href;
				if (href.indexOf(this.mobile_url)) {
					location.href = href.substr(0, href.indexOf("sp"));
				}
			}
		}

}
