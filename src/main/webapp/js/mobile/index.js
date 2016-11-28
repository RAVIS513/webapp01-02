/**
 * EndlessTrip for Mobile
 */
var HistSp = function() {
	// 定数定義
	this.container = '#sp_index_container';
	this.logo = '#sp_index_logo';
	this.logoImg = this.logo + ' img';
	this.header = '#sp_index_header';
	this.footer = '#sp_index_footer';
	this.contents = '#sp_index_main';
	this.tile = '.sp_index_tile';
}

HistSp.prototype = {

		/** メイン処理 **/
		main : function() {
			// ローカル変数
			var _this = this;

			// 画面リサイズ処理
			$(window).resize(function() {
				_this.setHeight();
			});

			this.setHeight();
		},

		/** コンテナ高さ設定 **/
		setHeight : function() {
			// コンテナ
			$(this.container).css('height', $(window).height() + 'px');

			// Logo
			var ph = $(this.logo).height();
			var ch = $(this.logoImg).height();
			$(this.logoImg).css('margin-top',(ph-ch)/2 + 'px');

			// Header
			$(this.header).css('line-height', $(this.header).height() + 'px');

			// Footer
			$(this.footer).css('line-height', $(this.footer).height() + 'px');

			// Tile
			var mh = $(this.contents).height();
			$(this.tile).each(function(index, obj) {
				$(obj).css({
					'heignt':mh/3 + 'px',
					'line-height':mh/3 + 'px'
				});
			});
		}
}