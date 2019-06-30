var ID;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    major:'',
    class:'',
    realname:'',
    sID:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    ID=options.id;
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
  majorInput:function(e){
    this.setData({
      major: e.detail.value
    })
  },
  classInput: function (e) {
    this.setData({
      class: e.detail.value
    })
  },
  realnameInput: function (e) {
    this.setData({
      realname: e.detail.value
    })
  },
  sID: function (e) {
    this.setData({
      sID: e.detail.value
    })
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
  my: function() {
    wx.navigateTo({
      url: '../index/index'
    })
  },
  rzcg: function(e) {
    wx.request({
      url: 'http://132.232.119.136:81/php/updata/realname.php',
      data:{
        id:ID,
        major: this.data.major,
        class: this.data.class,
        reaname: this.data.realname,
        studyId: this.data.sID
      },
      success:function(data){
          wx.navigateTo({
            url: 'rzcg',
          })
      }
    })
  }
})