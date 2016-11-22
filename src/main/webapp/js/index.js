/**
 * hist用クラス
 */
var Hist = function(container,sectionNum,iframeNum) {
	// クラス定義
	this.common = new Common();

	// 定数定義
	this.body = 'html,body';
	this.container = container;
	this.section = this.container + " .section";
	this.sectionNum = sectionNum - 1;
	this.sectionSpeed = 400;
	this.iframe = 'iframe.twitter-timeline';
	this.iframeNum = iframeNum;
	this.iframeCheckTimes = 10;
	this.iframeCheckTerm = 350;	// ms
	this.news = '#p1_newsMain';
	this.newsTitle = '.p1_newsTitle';
	this.newsSpeed = 1000;
	this.newsScrollTerm = 5000;	// ms

	// 変数定義
	this.currentPageNum = 0;
	this.iframeReady = false;
	this.iframeError = false;
}

Hist.prototype = {

	/** 初期処理 **/
	init : function() {
		// ローカル変数
		var _this = this;

		// ページ内容非表示
		this.common.contentHide(_this.container);
		this.watchIframe(_this, 0);
	},

	/** メイン処理 **/
	main : function() {
		// ローカル変数
		var _this = this;

		// windowリサイズ処理
		$(window).resize(function() {
			_this.adjustScrollPos(_this);
			_this.setHeight(_this);
			_this.common.textVerAlign();
			_this.common.adjustVerticalWindow();
			_this.common.adjustVerticalParent();
			_this.common.adjustModalContent();
			_this.adjustNewsTitle(_this);
		});

		// スクロール位置リセット
		this.resetScrollPos(_this);

		// セクション高さ設定
		this.setHeight(_this);

		// セクションスクロール処理
		this.getScrollEvent(_this);

		// ニューススクロール処理
		this.runScrollNews(_this);

		// モーダル処理
		this.common.runModal(_this);

		// ページ内容表示
		this.common.contentShow(this.container);

		// ニュースタイトル調整
		this.adjustNewsTitle(_this);

		// その他処理
		this.common.textVerAlign();
		this.common.adjustVerticalWindow();
		this.common.adjustVerticalParent();
	},

	/** セクション高さ設定 **/
	setHeight : function(_this) {
		$(_this.container).children(_this.section).each(function(index, obj) {
			$(obj).css('height',$(window).height());
		});
	},

	/** 画面スクロール位置リセット **/
	resetScrollPos : function(_this) {
		$(_this.body).stop().animate({scrollTop:0},'1');
	},

	/** 画面スクロール位置調整 **/
	adjustScrollPos : function(_this) {
		var num = _this.getCurrentPageNum();
		$(_this.body).stop().animate({scrollTop:($(window).height() * num)},'1');
	},

	/** 画面スクロールイベント取得 **/
	getScrollEvent : function(_this) {
		var mousewheelevent = 'onwheel' in document ? 'wheel' : 'onmousewheel' in document ? 'mousewheel' : 'DOMMouseScroll';
		$(document).on(mousewheelevent, function(e) {
			// ホイールイベント以外をキャンセル
			e.preventDefault();
			e.stopImmediatePropagation();
			// スクロール処理
			_this.runScroll(_this, e);
		});
	},

	/** 画面スクロールイベント取得 on iframe **/
	getScrollEventOnIframe : function(_this, iframeObj) {
		var mousewheelevent = 'onwheel' in document ? 'wheel' : 'onmousewheel' in document ? 'mousewheel' : 'DOMMouseScroll';
		var body = $(iframeObj).contents().find('body');
		$(body).on(mousewheelevent, function(e) {
			// ホイールイベント以外をキャンセル
			e.preventDefault();
			e.stopImmediatePropagation();
			// スクロール処理
			_this.runScroll(_this, e);
		});
	},

	/** 画面スクロール処理 **/
	runScroll : function(_this, event) {
		if (!($(_this.body).is(':animated'))) {
			var delta = event.originalEvent.deltaY ? -(event.originalEvent.deltaY) : event.originalEvent.wheelDelta ? event.originalEvent.wheelDelta : -(event.originalEvent.detail);
			var top = $(document).scrollTop();
			var wdh = $(window).height();
			var num = _this.getCurrentPageNum();
			if (delta < 0) {
				// マウスホイールを下にスクロール時
//				$(_this.body).stop().animate({scrollTop:(top + wdh)}, _this.sectionSpeed, 'linear');
				$(_this.body).animate({scrollTop:(top + wdh)}, _this.sectionSpeed, 'swing');
				if (num < _this.sectionNum) {
					num++;
				}
			} else {
				// マウスホイールを上にスクロール時
//				$(_this.body).stop().animate({scrollTop:(top - wdh)}, _this.sectionSpeed, 'linear');
				$(_this.body).animate({scrollTop:(top - wdh)}, _this.sectionSpeed, 'swing');
				if (num > 0) {
					num--;
				}
			}
			// 現在ページ位置再設定
			_this.setCurrentPageNum(num);
		}
	},

	/** iFrame監視および関連処理登録 **/
	watchIframe : function(_this, cnt) {
		try {
			// iframe要素の準備が完了しているか確認
			if ($(_this.iframe).length < _this.iframeNum) {
				$.error("iframe_length_zero");
			}
			$(_this.iframe).each(function(index, obj) {
				// iframe内のBodyコンテンツの読み込みが完了しているか確認
				if ($(obj).contents().find('body').html() == "") {
					$.error("iframe_body_empty");
				}
				// iframe読み込み完了 & iframe関連処理登録
				_this.getScrollEventOnIframe(_this, obj);
				_this.setIframeReady(true);
			});
		} catch (e) {
			// ポーリング回数カウント
			cnt++;
			// ポーリング 指定回以上行った場合、読み込みエラーとする
			if (cnt < _this.iframeCheckTimes) {
				setTimeout(function() {
					_this.watchIframe(_this, cnt);
				}, _this.iframeCheckTerm);
			} else {
				// iframe読み込みエラー
				_this.setIframeError(true);
				// TODO Error時処理追加
			}
		}
	},

	/** ニュースコンテンツスクロール処理 **/
	runScrollNews : function(_this) {
		setInterval(function() {
			$(_this.news).children().each(function(index, obj) {
				if (index == 0) {
					// スライドアウト
					$(obj).stop().animate({'marginLeft':$(window).width()}, _this.newsSpeed, 'linear', function() {
						$(obj).appendTo($(_this.news));
						$(obj).css('margin-left', 0);
						return false;
					});
				}
			});
		}, _this.newsScrollTerm);
	},

	/** ニュースタイトル縦揃え **/
	adjustNewsTitle : function(_this) {
		$(_this.newsTitle).each(function(index, obj) {
			var ch = $(obj).height();
			var ph = $(obj).parent().height();
			var h = ((ph - ch)/2);
			if (ch < ph) {
				$(obj).css('padding-top', h + "px");
			}
		});
	},

	/** メンバ変数 : currentPageNum 取得 **/
	getCurrentPageNum : function() {
		return this.currentPageNum;
	},

	/** メンバ変数 : currentPageNum 設定 **/
	setCurrentPageNum : function(currentPageNum) {
		this.currentPageNum = currentPageNum;
	},

	/** メンバ変数 : iframeReady 取得 **/
	getIframeReady : function() {
		return this.iframeReady;
	},

	/** メンバ変数 : iframeReady 設定 **/
	setIframeReady : function(iframeReady) {
		this.iframeReady = iframeReady;
	},

	/** メンバ変数 : iframeError 取得 **/
	getIframeError : function() {
		return this.iframeError;
	},

	/** メンバ変数 : iframeError 設定 **/
	setIframeError : function(iframeError) {
		this.iframeError = iframeError;
	}
}