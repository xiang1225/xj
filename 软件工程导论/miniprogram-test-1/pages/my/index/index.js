var postdata = require('../../../data/data.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    ysm: '（已实名认证）',
    RealName: '',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.setData({
      id: postdata.user.id,
    });
    var that = this;
    wx.request({
      url: 'http://132.232.119.136:81/php/data/user.php', //服务器的地址，现在微信小程序只支持https请求，所以调试的时候请勾选不校监安全域名
      data: {
        id: that.data.id
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function(res) {
        that.setData({
          user: res.data
        })
        that.setData({
          RealName: '(' + that.data.user.realName + ')'
        })
        if (that.data.user.AdminID == 0) {
          that.setData({
            ysm: '',
            RealName: ''
          })
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {},

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  news: function() {
    wx.navigateTo({
      url: '../../news/news'
    })
  },
  pubLish: function() {
    wx.navigateTo({
      url: '../../publish/content/content'
    })
  },
  home: function() {
    wx.navigateTo({
      url: '../../index/index'
    })
  },
  mycollection: function() {
    if (this.data.user.AdminID == 0) {
      wx.showToast({
        title: '您尚未实名认证，请先实名认证',
        icon: 'none',
        duration: 2000
      })
    } else {
      wx.navigateTo({
        url: '../mycollection/mycollection'
      })
    }
  },
  mypublish: function() {
    if (this.data.user.AdminID == 0) {
      wx.showToast({
        title: '您尚未实名认证，请先实名认证',
        icon: 'none',
        duration: 2000
      })
    } else {
      wx.navigateTo({
        url: '../mypublish/mypublish'
      })
    }
  },
  preImg: function(event) {
    var src = event.currentTarget.dataset.src; //获取data-src
    var imgList = event.currentTarget.dataset.list; //获取data-list
    var array = new Array();
    array[0] = imgList;
    //图片预览
    wx.previewImage({
      current: src, // 当前显示图片的http链接
      urls: array // 需要预览的图片http链接列表
    })
  },
  realname: function() {
    if (this.data.user.AdminID == 0) {
      wx.navigateTo({
        url: '../realname/xyrz?id=' + this.data.user.id,
      })
    } else {
      wx.showToast({
        title: '您已实名认证',
        icon: 'none',
        duration: 2000
      })
    }
  }
})