var postsData = require('../../data/data.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    post_key:'',
    clAss:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      user:postsData.user
    })
    var that = this;
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
    if(options.id!=null){
      that.setData({
        classb: postsData.cLass,
      })
      var i, xj;
      for (i = 0; i < 5; i++) {
        if (options.id == that.data.classb[i].id) {
          xj = that.data.classb[i].jvalue;
          break;
        }
      }
      wx.setNavigationBarTitle({
        title: xj,
      })
      wx.request({
        url: 'http://132.232.119.136:81/php/data/fenl.php',
        data: {
          fenl: options.id,
        },
        success: function (res) {
          that.setData({
            post_key: res.data.goods,
          })
        }
      })
    }else{
      var serch=options.serch;
      wx.request({
        url: 'http://132.232.119.136:81/php/data/serch.php',
        data:{
          search:serch,
        },
        success: function (res) {
          console.log(res.data);
          that.setData({ 
            post_key: res.data.goods,
          })
        }
      })
    }
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
  onPostTap: function (event) {
    var postId = event.currentTarget.dataset.postid;
    if (this.data.user.AdminID == 0) {
      wx.showToast({
        title: '您尚未实名认证，请先实名认证，在进行以下操作',
        icon: 'none',
        duration: 2000,
        success: function () {
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
})