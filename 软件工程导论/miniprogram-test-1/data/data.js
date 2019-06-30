var commodity ={
  goods:''
}
var classification = [{
  id: 'book',
  cid: '0',
  value: '闲置课本',
  jvalue: '课本',
  img: '/img/book.png'
}, {
  id: 'cloth',
  cid: '1',
  value: '闲置服饰',
  jvalue: '服饰',
  img: '/img/cloth.png'
}, {
  id: 'daily',
  cid: '2',
  value: '闲置日用品',
  jvalue: '日用品',
  img: '/img/daily.png'
}, {
  id:'sport',
  cid: '3',
  value: '闲置文体用品',
  jvalue: '文体',
  img: '/img/sport.png'
}, {
  id:'else',
  cid: '4',
  value: '其他闲置物品',
  jvalue: '其他',
  img: '/img/else.png'
}]
var user={
  id:'',
  name:'',
  imgurl:'',
  admin:'0',
  realname:'',
  signature:'',
  studyid:'',
  gread:'',
  speciality:'',
}
wx.login({
  success: function (res) {
    var code = res.code;//发送给服务器的code
    wx.getUserInfo({
      success: function (res) {
        var userNick = res.userInfo.nickName;//用户昵称
        var avataUrl = res.userInfo.avatarUrl;//用户头像地址
        if (code) {
          wx.request({
            url: 'http://132.232.119.136:81/php/data/login.php',//服务器的地址，现在微信小程序只支持https请求，所以调试的时候请勾选不校监安全域名
            data: {
              code: code,
              nick: userNick,
              avaurl: avataUrl
            },
            header: {
              'content-type': 'application/x-www-form-urlencoded'
            },
            success: function (res) {
              if (res.data.AdminID!=0){
                user.id=res.data.id;
                user.name = res.data.user;
                user.imgurl = res.data.imgurl;
                user.gread = res.data.gread;
                user.admin = res.data.AdminID;
                user.signature = res.data.substring;
                user.speciality = res.data.speciality;
                user.studyid=res.data.studyid;
                user.realname=res.data.realName;
                user.signature=res.data.signature;
              }else{
                user.id = res.data.id;
                user.name = res.data.user;
                user.imgurl = res.data.imgurl;
                user.admin = res.data.AdminID;
              }
            }
          })
          wx.request({
            url: 'http://132.232.119.136:81/php/data/goods.php',
            header: {
              'content-type': 'application/x-www-form-urlencoded'
            },
            success:function(res){
              commodity.goods = res.data.goods;
            }
          })
        }
        else {
          console.log("获取数据失败！");
        }
      }
    })
  },
  fail: function (error) {
    console.log('login failed ' + error);
  }
})
module.exports = {
  postList: commodity,
  cLass: classification,
  user:user
}