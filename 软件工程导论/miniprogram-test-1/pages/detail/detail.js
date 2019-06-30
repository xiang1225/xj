var postsData = require('../../data/data.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    cimg:'/img/collection.png',
    seller:'',
    Gimg:'',
    color:'#409d6e',
    text:'收 藏'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var postId = options.id;
    var that = this;
    this.data.currentPostId = postId;
    var i;
    var postData;
    wx.request({
      url: 'http://132.232.119.136:81/php/data/goods.php',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        that.setData({
          post_key: res.data.goods
        })
        for (i = 0; i < that.data.post_key.length; i++) {
          if (that.data.post_key[i].goodsID == postId) {
            postData = that.data.post_key[i];
          }
        }
        that.setData({
          posData: postData,
          user: postsData.user
        })
        var Id = postData.id;
        wx.request({
          url: 'http://132.232.119.136:81/php/goods/seller.php',
          data: {
            id: Id,
          },
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          success: function (res) {
            that.setData({
              seller: res.data,
            })
          }
        })
        var gid = that.data.posData.goodsID;
        wx.request({
          url: 'http://132.232.119.136:81/php/goods/img.php',
          data: {
            id: gid,
          },
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          success: function (res) {
            that.setData({
              Gimg: res.data,
            })
          }
        })
        wx.request({
          url: 'http://132.232.119.136:81/php/goods/isco.php',
          data: {
            uid: that.data.user.id,
            Gid: gid
          },
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          success: function (res) {
            if (res.data == 1) {
              that.setData({
                color: 'red',
                text: '已 收 藏',
                cimg: '/img/ycollection.png'
              })
            }
          }
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
  home:function(){
    wx.navigateTo({
      url: '../index/index',
    })
  },
  collection:function(){
    var that=this;
    if (that.data.text=='收 藏')
    {
      wx.showModal({
        title: '收藏',
        content: '您是否收藏该商品',
        showCancel: 'true',
        cancelColor: '#333',
        cancelText: '取消',
        confirmText: '确认',
        confirmColor: '#405f80',
        success: function (res) {
          if (res.confirm) {
            wx.request({
              url: 'http://132.232.119.136:81/php/updata/collection.php',
              data:{
                uid:that.data.user.id,
                gid: that.data.posData.goodsID
              },
              header: {
                'content-type': 'application/x-www-form-urlencoded'
              },
              success(res) {
                  console.log(res.data);
                  that.setData({
                    color: 'red',
                    text: '已 收 藏',
                    cimg:'/img/ycollection.png'
                  });
              }
            })
          } 
        }
      })
    }
    else{
      wx.showModal({
        title: '收藏',
        content: '您是否取消收藏该商品',
        showCancel: 'true',
        cancelColor: '#333',
        cancelText: '取消',
        confirmText: '确认',
        confirmColor: '#405f80',
        success: function (res) {
          if (res.confirm) {
            wx.request({
              url: 'http://132.232.119.136:81/php/updata/qcollection.php',
              data: {
                uid: that.data.user.id,
                gid: that.data.posData.goodsID
              },
              header: {
                'content-type': 'application/x-www-form-urlencoded'
              },
              success(res) {
                that.setData({
                  color: '#409d6e',
                  text: '收 藏',
                  cimg:'/img/collection.png'
                });
              }
            })
          }
        }
      })
    }
  },
  phone: function () {
    wx.makePhoneCall({
      phoneNumber: this.data.posData.contant,
    })
  },
  preImg: function (event) {
    var src = event.currentTarget.dataset.src;//获取data-src
    var imgList = event.currentTarget.dataset.list;//获取data-list
    var array=new Array();
    array[0]=imgList;
    //图片预览
    wx.previewImage({
      current: src, // 当前显示图片的http链接
      urls: array // 需要预览的图片http链接列表
    })
  }
})