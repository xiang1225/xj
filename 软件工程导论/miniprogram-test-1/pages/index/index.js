var postdata = require('../../data/data.js')
Page({
  /**
   * 页面的初始数据
   */
  data: {
    scroll_view_height: 0,
    serch: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    this.setData({
      post_key: postdata.postList,
      class: postdata.cLass,
      user: postdata.user
    });
    wx.request({
      url: 'http://132.232.119.136:81/php/data/goods.php',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        that.setData({
          post_key:res.data
        })
      }
    })
    wx.request({
      url: 'http://132.232.119.136:81/php/data/user.php', //服务器的地址，现在微信小程序只支持https请求，所以调试的时候请勾选不校监安全域名
      data: {
        id: that.data.user.id
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        that.setData({
          user: res.data
        })
      }
    })
    // 获取系统信息
    wx.getSystemInfo({
      success: function(res) {
        // 计算主体部分高度,单位为px
        var hetghta;
        var hetghtb;
        var query = wx.createSelectorQuery();
        query.select('.hd').boundingClientRect(rect => {
          that.setData({
            // second部分高度 = 利用窗口可使用高度 - first部分高度（这里的高度单位为px，所有利用比例将300rpx转换为px）
            scroll_view_height: res.windowHeight - rect.height
          })
        }).exec();
        query.select('.ft').boundingClientRect(rect => {
          that.setData({
            // second部分高度 = 利用窗口可使用高度 - first部分高度（这里的高度单位为px，所有利用比例将300rpx转换为px）
            scroll_view_height: that.data.scroll_view_height - rect.height
          })
        }).exec();
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
  onShow: function() {

  },

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
  pubLish: function() {
    if (this.data.user.AdminID == 0) {
      wx.showToast({
        title: '您尚未实名认证，请先实名认证，在进行以下操作',
        icon: 'none',
        duration: 2000,
        success: function() {
          setTimeout(function() {
            wx.navigateTo({
              url: '../my/index/index',
            })
          }, 2000)
        }
      })
    } else {
      wx.navigateTo({
        url: '../publish/content/content'
      })
    }
  },
  onPostTap: function(event) {
    var postId = event.currentTarget.dataset.postid;
    if (this.data.user.AdminID == 0) {
      wx.showToast({
        title: '您尚未实名认证，请先实名认证，在进行以下操作',
        icon: 'none',
        duration: 2000,
        success: function() {
          setTimeout(function () {
            wx.navigateTo({
              url: '../my/index/index',
            })
          }, 2000)
        }
      })
    } else {
      wx.navigateTo({
        url: '../detail/detail?id=' + postId,
      })
    }
  },
  news: function() {
    wx.navigateTo({
      url: '../news/news'
    })
  },
  my: function() {
    wx.navigateTo({
      url: '../my/index/index'
    })
  },
  fenl: function(event) {
    var fenl = event.currentTarget.dataset.id;
    wx.navigateTo({
      url: '../serch/serch?id=' + fenl,
    })
  },
  serch: function(e) {
    this.setData({
      serch: e.detail.value
    })
  },
  sercha: function(e) {
    var text = this.data.serch;
    wx.navigateTo({
      url: '../serch/serch?serch=' + text,
    })
  }
})