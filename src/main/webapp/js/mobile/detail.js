/**
 * EndlessTrip for Mobile
 */
var HistSpDetail = function() {
	// クラス定義
	this.common = new Common();
}

HistSpDetail.prototype = {

		/** メイン処理 **/
		main : function() {
			// ローカル変数
			var _this = this;

			// windowリサイズ処理
			$(window).resize(function() {
				_this.common.adjustVerticalParent();
			});

			this.common.adjustVerticalParent();

		}

}