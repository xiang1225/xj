var postdata = require('../../../data/data.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    items: [{
        name: 'fl',
        value: '课本',
        id: 'book'
      },
      {
        name: 'fl',
        value: '服饰',
        id: 'cloth'
      },
      {
        name: 'fl',
        value: '日用品',
        id: 'daily'
      },
      {
        name: 'fl',
        value: '文体',
        id: 'sport'
      },
      {
        name: 'fl',
        value: '其他',
        id: 'else'
      },
    ],
    images: [],
    hiddenName: false,
    name: '',
    number: '',
    gdetail: '',
    yprice: '',
    sprice: '',
    phone: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.setData({
      id: postdata.user.id,
    });
    var postId = options.id;
    var IteMs = this.data.items;
    IteMs[postId].checked = 'true';
    this.setData({
      items: IteMs
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
  home: function() {
    wx.navigateTo({
      url: '../../index/index'
    })
  },
  nameinput: function(e) {
    this.setData({
      name: e.detail.value
    })
  },
  numberinput: function(e) {
    this.setData({
      number: e.detail.value
    })
  },
  gdetailinput: function(e) {
    this.setData({
      gdetail: e.detail.value
    })
  },
  ypriceinput: function(e) {
    this.setData({
      yprice: e.detail.value
    })
  },
  spriceinput: function(e) {
    this.setData({
      sprice: e.detail.value
    })
  },
  phoneinput: function(e) {
    this.setData({
      phone: e.detail.value
    })
  },
  updataimg: function(e) {
    var that=this;
    wx.chooseImage({
      count: 4,
      sizeType: ['original', 'compressed'], //可选择原图或压缩后的图片
      sourceType: ['album', 'camera'], //可选择性开放访问相册、相机
      success: function(res) {
        that.setData({
          images: that.data.images.concat(res.tempFilePaths),
        })
        const tempFilePaths = res.tempFilePaths;
      }
    })
    that.setData({
      hiddenName: true,
    })
  },
  updata: function(e) {
    var that = this;
    var fenl;
    var i;
    for (i = 0; i < 5; i++) {
      if (that.data.items[i].checked == 'true') {
        fenl = that.data.items[i].id;
      }
    }
    wx.request({
      url: 'http://132.232.119.136:81/php/updata/upload.php',
      data: {
        id: that.data.id,
        name: that.data.name,
        number: that.data.number,
        Oprice: that.data.yprice,
        price: that.data.sprice,
        contant: that.data.gdetail,
        phone: that.data.phone,
        fenl: fenl,
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function(res) {
        for (i = 0; i < that.data.images.length; i++) {
          wx.uploadFile({
            url: 'http://132.232.119.136:81/php/updata/updataimg.php',
            filePath: that.data.images[i],
            name: 'file',
            formData: {
              goodsname: that.data.name,
              xu:i
            },
            success: function(res) {
              wx.showToast({
                title: '发布成功',
                icon: 'success',
                duration: 2000,
                success:function(){
                  setTimeout(function () {
                    wx.navigateTo({
                      url: '../../index/index',
                    })
                  }, 2000)
                }
              })
            }
          })
        }
      }
    })
  }
})