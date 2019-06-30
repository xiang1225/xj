var postsData = require('../../data/data.js')
Page({
  data: {
    tx: '',
    nc: '',
  },
  onTap:function(){
    wx.navigateTo({
      url: '../index/index',
    })
  },
  onLoad: function (options) {
    var that=this;
    wx.getUserInfo({
      success: function (res) {
        var userNick = res.userInfo.nickName;//用户昵称
        var avataUrl = res.userInfo.avatarUrl;//用户头像地址
        that.setData({
          tx: avataUrl,
          nc: userNick
        })
      }
    })
  }
})