var postdata = require('../../../data/data.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      post_key: postdata.postList,
      class: postdata.cLass,
      user: postdata.user
    });
    var that = this;
    wx.getSystemInfo({
      success: function (res) {
        var query = wx.createSelectorQuery();
        query.select('.ft').boundingClientRect(rect => {
          that.setData({
            // second部分高度 = 利用窗口可使用高度 - first部分高度（这里的高度单位为px，所有利用比例将300rpx转换为px）
            scroll_view_height: res.windowHeight - rect.height
          })
        }).exec();
      }
    })
    wx.request({
      url: 'http://132.232.119.136:81/php/data/goods.php',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        that.setData({
          post_key: res.data
        })
        var i;
        var goods = that.data.post_key.goods;
        var user = that.data.user.id;
        var pgoods = new Array();
        var n = 0;
        for (i = 0; i < goods.length; i++) {
          if (goods[i].id == user) {
            pgoods[n] = goods[i];
            n++;
          }
        }
        that.setData({
          post_key: pgoods,
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  news: function () {
    wx.navigateTo({
      url: '../../news/news'
    })
  },
  pubLish: function () {
    wx.navigateTo({
      url: '../../publish/content/content'
    })
  },
  home: function () {
    wx.navigateTo({
      url: '../../index/index'
    })
  },
  my: function () {
    wx.navigateTo({
      url: '../index/index'
    })
  },
  onPostTap: function (event) {
    var postId = event.currentTarget.dataset.postid;
    console.log(postId);
    wx.navigateTo({
      url: '../../detail/detail?id=' + postId,
    })
  },
})