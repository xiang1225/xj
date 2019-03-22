$(document).ready(function(){
	$("#tj").click(function(){
		$a=$("#sr").val();
		$b=$("#srf").val();
		var operator=new Array(4);
		operator[0]="+";
		operator[1]="-";
		operator[2]="*";
		operator[3]="÷";
		$("#wb").append("<p>2017012578\r</p>");
//		if($b>0)
//		{
//			while($b--)
//			{
//				var txt=new Array(15);
//				var i;
//				var sun=new Array(3);
//				for(i=0;i<15;i++)
//				{
//					if(i%2==0)
//					{
//						txt[i]=Math.round(Math.random()*100);
//					}
//					else if((i-1)%4==0)
//					{
//						txt[i]="/";
//					}
//					else{
//						txt[i]=operator[Math.round(Math.random()*3)];
//					}
//				}
//				while(txt[3]==txt[7]&&txt[7]==txt[11])
//				{
//					txt[3]=operator[Math.round(Math.random()*3)];
//				}
//				function zhuan(number1, number2){
//					// 创建一个表示余数的变量
//					var remainder = 0;
//					// 通过循环计算
//					do {
//  				// 更新当前余数
//  					remainder = number1 % number2;
//  					// 更新数字1
//  					number1 = number2;
//  					// 更新数字1
//  					number2 = remainder;
//					} while(remainder !== 0);
//					return number1;
//				}
//				if(txt[3]=="+"||txt[3]=="-")
//				{
//					if(txt[7]=="+"||txt[7]=="-")
//					{
//						if(txt[11]=="+"||txt[11]=="-")
//						{
//							if(txt[3]=="-")
//							{
//								sun[2]=txt[2]*txt[6];
//								sun[0]=txt[0]*txt[6]-txt[3]*txt[2];
//								while(sun[0]<0)
//								{
//									txt[0]=Math.round(Math.random()*100);
//									sun[0]=txt[0]*txt[6]-txt[3]*txt[2];
//								}
//								if(txt[7]=="-")
//								{
//									sun[2]=sun[2]*txt[10];
//									var y;
//									y=sun[0]*txt[10]-txt[8]*sun[2];
//									while(y<0){
//										txt[8]=Math.round(Math.random()*100);
//										y=sun[0]*txt[10]-txt[8]*sun[2];
//									}
//									sun[0]=y;
//									if(txt[11]=="-")
//									{
//										sun[2]=sun[2]*txt[14];
//										y=sun[0]*txt[14]-txt[12]*sun[2];
//										while(y<0){
//											txt[12]=Math.round(Math.random()*100);
//											y=sun[0]*txt[14]-txt[12]*sun[2];
//										}
//										sun[0]=y;
//									}
//									if(txt[11]=="+")
//									{
//										sun[2]=sun[2]*txt[14];
//										sun[0]=sun[0]*txt[14]+txt[12]*sun[2];
//									}
//								}
//								if(txt[7]=="+")
//								{
//									sun[2]=sun[2]*txt[10];
//									sun[0]=sun[0]*txt[10]+txt[8]*sun[2];
//									if(txt[11]=="-")
//									{
//										var y;
//										sun[2]=sun[2]*txt[14];
//										y=sun[0]*txt[14]-txt[12]*sun[2];
//										while(y<0){
//											txt[12]=Math.round(Math.random()*100);
//											y=sun[0]*txt[14]-txt[12]*sun[2];
//										}
//										sun[0]=y;
//									}
//									if(txt[11]=="+")
//									{
//										sun[2]=sun[2]*txt[14];
//										sun[0]=sun[0]*txt[14]+txt[12]*sun[2];
//									}
//								}
//							}
//							if(txt[3]=="+")
//							{
//								sun[2]=txt[2]*txt[6];
//								sun[0]=txt[0]*txt[6]+txt[2]*txt[4];
//								if(txt[7]=="-")
//								{
//									sun[2]=sun[2]*txt[10];
//									var y;
//									y=sun[0]*txt[10]-txt[8]*sun[2];
//									while(y<0){
//										txt[8]=Math.round(Math.random()*100);
//										y=sun[0]*txt[10]-txt[8]*sun[2];
//									}
//									sun[0]=y;
//									if(txt[11]=="-")
//									{
//										sun[2]=sun[2]*txt[14];
//										y=sun[0]*txt[14]-txt[12]*sun[2];
//										while(y<0){
//											txt[12]=Math.round(Math.random()*100);
//											y=sun[0]*txt[14]-txt[12]*sun[2];
//										}
//										sun[0]=y;
//									}
//									if(txt[11]=="+")
//									{
//										sun[2]=sun[2]*txt[14];
//										sun[0]=sun[0]*txt[14]+txt[12]*sun[2];
//									}
//								}
//								if(txt[7]=="+")
//								{
//									sun[2]=sun[2]*txt[10];
//									sun[0]=sun[0]*txt[10]+txt[8]*sun[2];
//									if(txt[11]=="-")
//									{
//										var y;
//										sun[2]=sun[2]*txt[14];
//										y=sun[0]*txt[14]-txt[12]*sun[2];
//										while(y<0){
//											txt[12]=Math.round(Math.random()*100);
//											y=sun[0]*txt[14]-txt[12]*sun[2];
//										}
//										sun[0]=y;
//									}
//									if(txt[11]=="+")
//									{
//										sun[2]=sun[2]*txt[14];
//										sun[0]=sun[0]*txt[14]+txt[12]*sun[2];
//									}
//								}
//							}
//						}
//						else{
//							if(txt[11]=="*")
//							{
//								sun[0]=txt[12]*txt[8];
//								sun[2]=txt[10]*txt[14];
//								if(txt[3]=="-")
//								{
//									var x,y;
//									x=txt[2]*txt[6];
//									y=txt[0]*txt[6]-txt[4]*txt[2];
//									while(y<0)
//									{
//										txt[4]=Math.round(Math.random()*100);
//										y=txt[0]*txt[6]-txt[4]*txt[2];
//									}
//									if(txt[7]=="-")
//									{
//										var s;
//										sun[2]=sun[2]*x;
//										s=y*sun[2]-sun[0]*x;
//										while(s<0)
//										{
//											while(y<0)
//											{
//												txt[4]=Math.round(Math.random()*100);
//												y=txt[0]*txt[6]-txt[4]*txt[2];
//											}
//											s=y*sun[2]-sun[0]*x;
//										}
//										sun[0]=s;
//									}
//									else
//									{
//										sun[2]=sun[2]*x;
//										sun[0]=sun[0]*x+sun[2]*y;
//									}
//								}
//								else{
//									var x,y;
//									x=txt[2]*txt[6];
//									y=txt[0]*txt[6]+txt[4]*txt[2];
//									if(txt[7]=="-")
//									{
//										var s;
//										sun[2]=sun[2]*x;
//										s=y*sun[2]-sun[0]*x;
//										while(s<0)
//										{
//											txt[4]=Math.round(Math.random()*100);
//											y=txt[0]*txt[6]+txt[4]*txt[2];
//											s=y*sun[2]-sun[0]*x;
//										}
//										sun[0]=s;
//									}
//									else
//									{
//										sun[2]=sun[2]*x;
//										sun[0]=sun[0]*x+sun[2]*y;
//									}
//								}
//							}
//							else{
//								sun[0]=txt[8]*txt[14];
//								sun[2]=txt[10]*txt[12];
//								if(txt[3]=="-")
//								{
//									var x,y;
//									x=txt[2]*txt[6];
//									y=txt[0]*txt[6]-txt[4]*txt[2];
//									while(y<0)
//									{
//										txt[4]=Math.round(Math.random()*100);
//										y=txt[0]*txt[6]-txt[4]*txt[2];
//									}
//									if(txt[7]=="-")
//									{
//										var s;
//										sun[2]=sun[2]*x;
//										s=y*sun[2]-sun[0]*x;
//										while(s<0)
//										{
//											while(y<0)
//											{
//												txt[4]=Math.round(Math.random()*100);
//												y=txt[0]*txt[6]-txt[4]*txt[2];
//											}
//											s=y*sun[2]-sun[0]*x;
//										}
//										sun[0]=s;
//									}
//									else
//									{
//										sun[2]=sun[2]*x;
//										sun[0]=sun[0]*x+sun[2]*y;
//									}
//								}
//								else{
//									var x,y;
//									x=txt[2]*txt[6];
//									y=txt[0]*txt[6]+txt[4]*txt[2];
//									if(txt[7]=="-")
//									{
//										var s;
//										sun[2]=sun[2]*x;
//										s=y*sun[2]-sun[0]*x;
//										while(s<0)
//										{
//											txt[4]=Math.round(Math.random()*100);
//											y=txt[0]*txt[6]+txt[4]*txt[2];
//											s=y*sun[2]-sun[0]*x;
//										}
//										sun[0]=s;
//									}
//									else
//									{
//										sun[2]=sun[2]*x;
//										sun[0]=sun[0]*x+sun[2]*y;
//									}
//								}
//							}
//						}
//					}
//					else{
//						if(txt[7]=="*")
//						{
//							sun[0]=txt[4]*txt[8];
//							sun[2]=txt[6]*txt[10];
//							if(txt[11]=="-"||txt[11]=="+")
//							{
//								if(txt[3]=="-")
//								{
//									var x,y;
//									y=txt[2]*sun[2];
//									x=txt[0]*sun[2]-sun[0]*txt[2];
//									while(x<0)
//									{
//										txt[0]=Math.round(Math.random()*100);
//										x=txt[0]*sun[2]-sun[0]*txt[2];
//									}
//									sun[0]=x;
//									sun[2]=y;
//									if(txt[11]=="-")
//									{
//										var x,y;
//										y=txt[14]*sun[2];
//										x=sun[0]*txt[14]-txt[12]*sun[2];
//										while(x<0)
//										{
//											txt[12]=Math.round(Math.random()*100);
//											x=sun[0]*txt[14]-txt[12]*sun[2];
//										}
//										sun[0]=x;
//										sun[2]=y;
//									}
//									else{
//										sun[2]=txt[14]*sun[2];
//										sun[0]=sun[0]*txt[14]+txt[12]*sun[2];
//									}
//								}
//								else{
//									sun[2]=txt[2]*sun[2];
//									sun[0]=txt[0]*sun[2]+sun[0]*txt[2];
//									if(txt[11]=="-")
//									{
//										var x,y;
//										y=txt[14]*sun[2];
//										x=sun[0]*txt[14]-txt[12]*sun[2];
//										while(x<0)
//										{
//											txt[12]=Math.round(Math.random()*100);
//											x=sun[0]*txt[14]-txt[12]*sun[2];
//										}
//										sun[0]=x;
//										sun[2]=y;
//									}
//									else{
//										sun[2]=txt[14]*sun[2];
//										sun[0]=sun[0]*txt[14]+txt[12]*sun[2];
//									}
//								}
//							}
//							else{
//								if(txt[11]=="*")
//								{
//									sun[2]=sun[2]*txt[16];
//									sun[0]=sun[0]*txt[14];
//									if(txt[3]=="-")
//									{
//										var x,y;
//										x=sun[2]*txt[2];
//										y=txt[0]*sun[2]-sun[0]*txt[2];
//										while(y<0){
//											txt[0]=Math.round(Math.random()*100);
//											y=txt[0]*sun[2]-sun[0]*txt[2];
//										}
//										sun[2]=x;
//										sun[0]=y;
//									}
//									else{
//										sun[2]=sun[2]*txt[2];
//										sun[0]=txt[0]*sun[2]+sun[0]*txt[2];
//									}
//								}
//								else{
//									sun[2]=sun[2]*txt[16];
//									sun[0]=sun[0]*txt[14];
//									if(txt[3]=="-")
//									{
//										var x,y;
//										x=sun[2]*txt[2];
//										y=txt[0]*sun[2]-sun[0]*txt[2];
//										while(y<0){
//											txt[0]=Math.round(Math.random()*100);
//											y=txt[0]*sun[2]-sun[0]*txt[2];
//										}
//										sun[2]=x;
//										sun[0]=y;
//									}
//									else{
//										sun[2]=sun[2]*txt[2];
//										sun[0]=txt[0]*sun[2]+sun[0]*txt[2];
//									}
//								}
//							}
//						}
//					}
//				}
//				
//				else{
//					if(txt[3]=="*")
//					{
//						sun[2]=txt[2]*txt[6];
//						sun[0]=txt[0]*txt[4];
//						if(txt[7]=="-"||txt[7]=="+")
//						{
//							if(txt[11]=="-"||txt[11]=="+")
//							{
//								if(txt[7]=="-")
//								{
//									var x,y;
//									x=sun[2]*txt[10];
//									y=sun[0]*txt[10]-txt[8]*sun[2];
//									while(y<0)
//									{
//										txt[8]=Math.round(Math.random()*100);
//										y=sun[0]*txt[10]-txt[8]*sun[2];
//									}
//									sun[0]=y;
//									sun[2]=x;
//									if(txt[11]=="-")
//									{
//										var x,y;
//										x=sun[2]*txt[14];
//										y=sun[0]*txt[14]-txt[12]*sun[2];
//										while(y<0)
//										{
//											txt[12]=Math.round(Math.random()*100);
//											y=sun[0]*txt[14]-txt[12]*sun[2];
//										}
//										sun[0]=y;
//										sun[2]=x;
//									}
//									else{
//										sun[0]=sun[2]*txt[14];
//										sun[2]=sun[0]*txt[14]+txt[12]*sun[2];
//									}
//								}
//								else{
//									sun[0]=sun[2]*txt[10];
//									sun[2]=sun[0]*txt[10]+txt[8]*sun[2];
//									if(txt[11]=="-")
//									{
//										var x,y;
//										x=sun[2]*txt[14];
//										y=sun[0]*txt[14]-txt[12]*sun[2];
//										while(y<0)
//										{
//											txt[12]=Math.round(Math.random()*100);
//											y=sun[0]*txt[14]-txt[12]*sun[2];
//										}
//										sun[0]=y;
//										sun[2]=x;
//									}
//									else{
//										sun[0]=sun[2]*txt[14];
//										sun[2]=sun[0]*txt[14]+txt[12]*sun[2];
//									}
//								}
//							}
//							else{
//								if(txt[11]=="*")
//								{
//									var x,y;
//									x=txt[10]*txt[14];
//									y=txt[8]*txt[12];
//									if(txt[7]=="-"){
//										var s1,s2;
//										s1=x*sun[2];
//										s2=sun[0]*x-y*sun[2];
//										while(s2<0)
//										{
//											txt[8]=Math.round(Math.random()*100);
//											y=txt[8]*txt[12];
//											s2=sun[0]*x-y*sun[2];
//										}
//										sun[0]=s2;
//										sun[2]=s1;
//									}
//									else{
//										sun[2]=x*sun[2];
//										sun[0]=sun[0]*x+y*sun[2];
//									}
//								}
//								else{
//									var x,y;
//									x=txt[10]*txt[12];
//									y=txt[8]*txt[14];
//									if(txt[7]=="-"){
//										var s1,s2;
//										s1=x*sun[2];
//										s2=sun[0]*x-y*sun[2];
//										while(s2<0)
//										{
//											txt[8]=Math.round(Math.random()*100);
//											y=txt[8]*txt[14];
//											s2=sun[0]*x-y*sun[2];
//										}
//										sun[0]=s2;
//										sun[2]=s1;
//									}
//									else{
//										sun[2]=x*sun[2];
//										sun[0]=sun[0]*x+y*sun[2];
//									}
//								}
//							}
//						}
//						else{
//							if(txt[7]=="*")
//							{
//								sun[0]=sun[0]*txt[8];
//								sun[2]=sun[2]*txt[10];
//								if(txt[11]=="-"){
//									var x,y;
//									x=sun[2]*txt[14];
//									y=sun[0]*txt[14]-sun[2]*txt[12];
//									while(y<0){
//										txt[12]=Math.round(Math.random()*100);
//										y=sun[0]*txt[14]-sun[2]*txt[12];
//									}
//									sun[0]=y;
//									sun[2]=x;
//								}
//								if(txt[11]=="+"){
//									sun[0]=sun[0]*txt[14]+sun[2]*txt[12];
//									sun[2]=sun[2]*txt[14];
//								}
//								if(txt[11]=="*"){
//									sun[0]=sun[0]*txt[12];
//									sun[2]=sun[2]*txt[14];
//								}
//								if(txt[11]=="÷"){
//									sun[0]=sun[0]*txt[14];
//									sun[2]=sun[2]*txt[12];
//								}
//								
//							}
//							else{
//								sun[0]=sun[0]*txt[10];
//								sun[2]=sun[2]*txt[8];
//								if(txt[11]=="-"){
//									var x,y;
//									x=sun[2]*txt[14];
//									y=sun[0]*txt[14]-sun[2]*txt[12];
//									while(y<0){
//										txt[12]=Math.round(Math.random()*100);
//										y=sun[0]*txt[14]-sun[2]*txt[12];
//									}
//									sun[0]=y;
//									sun[2]=x;
//								}
//								if(txt[11]=="+"){
//									sun[0]=sun[0]*txt[14]+sun[2]*txt[12];
//									sun[2]=sun[2]*txt[14];
//								}
//								if(txt[11]=="*"){
//									sun[0]=sun[0]*txt[12];
//									sun[2]=sun[2]*txt[14];
//								}
//								if(txt[11]=="÷"){
//									sun[0]=sun[0]*txt[14];
//									sun[2]=sun[2]*txt[12];
//								}
//							}
//						}
//					}
//					else{
//						sun[0]=txt[0]*txt[6];
//						sun[2]=txt[2]*txt[4];
//						if(txt[7]=="-"||txt[7]=="+")
//						{
//							if(txt[11]=="-"||txt[11]=="+")
//							{
//								if(txt[7]=="-")
//								{
//									var x,y;
//									x=sun[2]*txt[10];
//									y=sun[0]*txt[10]-txt[8]*sun[2];
//									while(y<0)
//									{
//										txt[8]=Math.round(Math.random()*100);
//										y=sun[0]*txt[10]-txt[8]*sun[2];
//									}
//									sun[0]=y;
//									sun[2]=x;
//									if(txt[11]=="-")
//									{
//										var x,y;
//										x=sun[2]*txt[14];
//										y=sun[0]*txt[14]-txt[12]*sun[2];
//										while(y<0)
//										{
//											txt[12]=Math.round(Math.random()*100);
//											y=sun[0]*txt[14]-txt[12]*sun[2];
//										}
//										sun[0]=y;
//										sun[2]=x;
//									}
//									else{
//										sun[0]=sun[2]*txt[14];
//										sun[2]=sun[0]*txt[14]+txt[12]*sun[2];
//									}
//								}
//								else{
//									sun[0]=sun[2]*txt[10];
//									sun[2]=sun[0]*txt[10]+txt[8]*sun[2];
//									if(txt[11]=="-")
//									{
//										var x,y;
//										x=sun[2]*txt[14];
//										y=sun[0]*txt[14]-txt[12]*sun[2];
//										while(y<0)
//										{
//											txt[12]=Math.round(Math.random()*100);
//											y=sun[0]*txt[14]-txt[12]*sun[2];
//										}
//										sun[0]=y;
//										sun[2]=x;
//									}
//									else{
//										sun[0]=sun[2]*txt[14];
//										sun[2]=sun[0]*txt[14]+txt[12]*sun[2];
//									}
//								}
//							}
//							else{
//								if(txt[11]=="*")
//								{
//									var x,y;
//									x=txt[10]*txt[14];
//									y=txt[8]*txt[12];
//									if(txt[7]=="-"){
//										var s1,s2;
//										s1=x*sun[2];
//										s2=sun[0]*x-y*sun[2];
//										while(s2<0)
//										{
//											txt[8]=Math.round(Math.random()*100);
//											y=txt[8]*txt[12];
//											s2=sun[0]*x-y*sun[2];
//										}
//										sun[0]=s2;
//										sun[2]=s1;
//									}
//									else{
//										sun[2]=x*sun[2];
//										sun[0]=sun[0]*x+y*sun[2];
//									}
//								}
//								else{
//									var x,y;
//									x=txt[10]*txt[12];
//									y=txt[8]*txt[14];
//									if(txt[7]=="-"){
//										var s1,s2;
//										s1=x*sun[2];
//										s2=sun[0]*x-y*sun[2];
//										while(s2<0)
//										{
//											txt[8]=Math.round(Math.random()*100);
//											y=txt[8]*txt[14];
//											s2=sun[0]*x-y*sun[2];
//										}
//										sun[0]=s2;
//										sun[2]=s1;
//									}
//									else{
//										sun[2]=x*sun[2];
//										sun[0]=sun[0]*x+y*sun[2];
//									}
//								}
//							}
//						}
//						else{
//							if(txt[7]=="*")
//							{
//								sun[0]=sun[0]*txt[8];
//								sun[2]=sun[2]*txt[10];
//								if(txt[11]=="-"){
//									var x,y;
//									x=sun[2]*txt[14];
//									y=sun[0]*txt[14]-sun[2]*txt[12];
//									while(y<0){
//										txt[12]=Math.round(Math.random()*100);
//										y=sun[0]*txt[14]-sun[2]*txt[12];
//									}
//									sun[0]=y;
//									sun[2]=x;
//								}
//								if(txt[11]=="+"){
//									sun[0]=sun[0]*txt[14]+sun[2]*txt[12];
//									sun[2]=sun[2]*txt[14];
//								}
//								if(txt[11]=="*"){
//									sun[0]=sun[0]*txt[12];
//									sun[2]=sun[2]*txt[14];
//								}
//								if(txt[11]=="÷"){
//									sun[0]=sun[0]*txt[14];
//									sun[2]=sun[2]*txt[12];
//								}
//								
//							}
//							else{
//								sun[0]=sun[0]*txt[10];
//								sun[2]=sun[2]*txt[8];
//								if(txt[11]=="-"){
//									var x,y;
//									x=sun[2]*txt[14];
//									y=sun[0]*txt[14]-sun[2]*txt[12];
//									while(y<0){
//										txt[12]=Math.round(Math.random()*100);
//										y=sun[0]*txt[14]-sun[2]*txt[12];
//									}
//									sun[0]=y;
//									sun[2]=x;
//								}
//								if(txt[11]=="+"){
//									sun[0]=sun[0]*txt[14]+sun[2]*txt[12];
//									sun[2]=sun[2]*txt[14];
//								}
//								if(txt[11]=="*"){
//									sun[0]=sun[0]*txt[12];
//									sun[2]=sun[2]*txt[14];
//								}
//								if(txt[11]=="÷"){
//									sun[0]=sun[0]*txt[14];
//									sun[2]=sun[2]*txt[12];
//								}
//							}
//						}
//					}
//				}
//				$("#wb").append("<p>"+txt[0]/zhuan(txt[0],txt[2])+txt[1]+txt[2]/zhuan(txt[0],txt[2])+txt[3]+txt[4]/zhuan(txt[4],txt[6])+txt[5]+txt[6]/zhuan(txt[4],txt[6])+txt[7]+txt[8]/zhuan(txt[8],txt[10])+txt[9]+txt[10]/zhuan(txt[8],txt[10])+txt[11]+txt[12]/zhuan(txt[14],txt[12])+txt[13]+txt[14]/zhuan(txt[14],txt[12])+"="+sun[0]/zhuan(sun[0],sun[2])+"/"+sun[2]/zhuan(sun[0],sun[2])+"\r</p>");
//			}
//		}
//		
		if($a>0)
		{
			while($a--)
			{
				var txt=new Array(7);
				var i;
				for(i=0;i<7;i++)
				{
					if(i%2==0)
					{
						txt[i]=Math.round(Math.random()*100);
					}
					else{
						txt[i]=operator[Math.round(Math.random()*3)];
					}
				}
				var j;
				for(i=3;i<7;i=i+2)
				while(txt[1]==txt[3]&&txt[3]==txt[5])
				{
					txt[3]=operator[Math.round(Math.random()*3)];
				}
				var x,s;
				s=0;
				if(txt[1]=="-"||txt[1]=="+")
				{
					if(txt[3]=="-"||txt[3]=="+")
					{
						if(txt[5]=="-"||txt[5]=="+")
						{
							if(txt[1]=="-")
							{
								while(txt[0]-txt[2]<0)
								{
									txt[2]=Math.round(Math.random()*100);
								}
								s=s+txt[0]-txt[2];
								if(txt[3]=="-")
								{
									while(s-txt[4]<0)
									{
										txt[4]=Math.round(Math.random()*100);
									}
									s=s-txt[4];
									if(txt[5]=="-")
									{
										while(s-txt[6]<0)
										{
											txt[6]=Math.round(Math.random()*100);
										}
										s=s-txt[6];
									}
									if(txt[5]=="+")
									{
										s=s+txt[6];
									}
								}
								if(txt[3]=="+")
								{
									s=s+txt[4];
									if(txt[5]=="-")
									{
										while(s-txt[6]<0)
										{
											txt[6]=Math.round(Math.random()*s);
										}
										s=s-txt[6];
									}
									if(txt[5]=="+")
									{
										s=s+txt[6];
									}
								}
							}
							if(txt[1]=="+")
							{
								s=s+txt[0]+txt[2];
								if(txt[3]=="-")
								{
									while(s-txt[4]<0)
									{
										txt[4]=Math.round(Math.random()*s);
									}
									s=s-txt[4];
									if(txt[5]=="-")
									{
										while(s-txt[6]<0)
										{
											txt[6]=Math.round(Math.random()*s);
										}
										s=s-txt[6];
									}
									if(txt[5]=="+")
									{
										s=s+txt[6];
									}
								}
								if(txt[3]=="+")
								{
									s=s+txt[4];
									if(txt[5]=="-")
									{
										while(s-txt[6]<0)
										{
											txt[6]=Math.round(Math.random()*s);
										}
										s=s-txt[6];
									}
									if(txt[5]=="+")
									{
										s=s+txt[6];
									}
								}
							}
						}
						if(txt[5]=="*"||txt[5]=="÷")
						{
							if(txt[5]=="*")
							{
								s=s+txt[4]*txt[6];
								if(txt[1]=="-")
								{
									var m;
									m=txt[0]-txt[2];
									while(m<0)
									{
										txt[2]=Math.round(Math.random()*txt[0]);
										m=txt[0]-txt[2];
									}
									if(txt[3]=="-")
									{
										while(s>=100)
										{
											txt[4]=Math.round(Math.random()*100);
											txt[6]=Math.round(Math.random()*100);
											s=txt[4]*txt[6];
										}
										while(m-s<0)
										{
												txt[2]=Math.round(Math.random()*100);
												txt[0]=Math.round(Math.random()*100);
												m=txt[0]-txt[2];
										}
										s=m-s;
									}
									if(txt[3]=="+")
									{
										s=m+s;
									}
								}
								if(txt[1]=="+")
								{
									var m;
									m=txt[2]+txt[0];
									if(txt[3]=="-")
									{
										while(s>=100)
										{
											txt[4]=Math.round(Math.random()*100);
											txt[6]=Math.round(Math.random()*100);
											s=txt[4]*txt[6];
										}
										while(m-s<0)
										{
											txt[0]=Math.round(Math.random()*(100));
											txt[2]=Math.round(Math.random()*(100));
											m=txt[2]+txt[0];
										}
										s=m-s;
									}
									if(txt[3]=="+")
									{
										s=m+s;
									}
								}
							}
							if(txt[5]=="÷")
							{
								while(txt[4]%txt[6]!=0||txt[6]==0)
								{
									txt[4]=Math.round(Math.random()*100);
									txt[6]=Math.round(Math.random()*100);
								}
								s=s+txt[4]/txt[6];
								if(txt[1]=="-")
								{
									while(txt[0]-txt[2]<0)
									{
										txt[2]=Math.round(Math.random()*100);
									}
									var s2;
									s2=txt[0]-txt[2];
									if(txt[3]=="-")
									{
										while(s2-s<0)
										{
											txt[2]=Math.round(Math.random()*100);
											txt[0]=Math.round(Math.random()*100);
											s2=txt[0]-txt[2];
										}
										s=s2-s;
									}
									if(txt[3]=="+")
									{
										s=s2+s
									}
								}
								if(txt[1]=="+")
								{
									var y;
									y=txt[2]+txt[0];
									if(txt[3]=="-")
									{
										while(y-s<0)
										{
											txt[0]=Math.round(Math.random()*100);
											txt[2]=Math.round(Math.random()*100);
											y=txt[2]+txt[0];
										}
										s=y-s;
									}
									if(txt[3]=="+")
									{
										s=y+s
									}
								}
							}
						}
					}
					if(txt[3]=="*"||txt[3]=="÷")
					{
						if(txt[3]=="*")
						{
							s=s+txt[2]*txt[4];
							if(txt[5]=="+"||txt[5]=="-")
							{
								if(txt[1]=="-")
								{
									while(s>=100)
									{
										txt[2]=Math.round(Math.random()*(100));
										txt[4]=Math.round(Math.random()*(100));
										s=txt[2]*txt[4];
									}
									while(txt[0]-s<=0)
									{
										txt[0]=Math.round(Math.random()*(100));
									}
									s=txt[0]-s;
									if(txt[5]=="-")
									{
										while(s-txt[6]<0)
										{
											txt[6]=Math.round(Math.random()*100);
										}
										s=s-txt[6];
									}
									if(txt[5]=="+")
									{
										s=s+txt[6];
									}
								}
								if(txt[1]=="+")
								{
									s=s+txt[0];
									if(txt[5]=="-")
									{
										while(s-txt[6]<0)
										{
											txt[6]=Math.round(Math.random()*s);
										}
										s=s-txt[6];
									}
									if(txt[5]=="+")
									{
										s=s+txt[6];
									}
								}
							}
							if(txt[5]=="*"||txt[5]=="÷")
							{
								if(txt[5]=="*")
								{
									s=s*txt[6];
									while(s>=100)
									{
										txt[2]=Math.round(Math.random()*100);
										txt[4]=Math.round(Math.random()*100);
										txt[6]=Math.round(Math.random()*100);
										s=txt[2]*txt[4]*txt[6];
									}
									if(txt[1]=="-")
									{
										while(txt[0]-s<0)
										{
											txt[0]=Math.round(Math.random()*(s+100));
										}
										s=txt[0]-s;
									}
									if(txt[1]=="+")
									{
										s=s+txt[0];
									}
								}
								if(txt[5]=="÷")
								{
									while(s%txt[6]!=0||txt[6]==0)
									{
										txt[6]=Math.round(Math.random()*100);
									}
									s=s/txt[6];
									if(txt[1]=="-")
									{
										while(s>=100)
										{
											txt[2]=Math.round(Math.random()*100);
											txt[4]=Math.round(Math.random()*100);
											while(txt[2]*txt[4]/txt[6]!=0||txt[6]==0)
											{
												txt[4]=Math.round(Math.random()*100);
											}
											s=txt[2]*txt[4]/txt[6];
										}
										while(txt[0]-s<0)
										{
											txt[0]=Math.round(Math.random()*(100));
										}
										s=txt[0]-s;
									}
									if(txt[1]=="+")
									{
										s=s+txt[0];
									}
								}
							}
						}
						if(txt[3]=="÷")
						{
							while(txt[2]%txt[4]!=0||txt[4]==0)
							{
								txt[4]=Math.round(Math.random()*txt[2]);
							}
							s=s+txt[2]/txt[4];
							if(txt[5]=="+"||txt[5]=="-")
							{
								if(txt[1]=="-")
								{
									while(txt[0]-s<0)
									{
										txt[0]=Math.round(Math.random()*100);
									}
									s=txt[0]-s;
									if(txt[5]=="-")
									{
										while(s-txt[6]<0)
										{
											txt[6]=Math.round(Math.random()*s);
										}
										s=s-txt[6];
									}
									if(txt[5]=="+")
									{
										s=s+txt[6];
									}
								}
								if(txt[1]=="+")
								{
									s=s+txt[0];
									if(txt[5]=="-")
									{
										while(s-txt[6]<0)
										{
											txt[6]=Math.round(Math.random()*s);
										}
										s=s-txt[6];
									}
									if(txt[5]=="+")
									{
										s=s+txt[6];
									}
								}
							}
							if(txt[5]=="*"||txt[5]=="÷")
							{
								if(txt[5]=="*")
								{
									s=s*txt[6];
									if(txt[1]=="-")
									{
										while(s>=100)
										{
											while(txt[2]%txt[4]!=0)
											{
												txt[4]=Math.round(Math.random()*txt[2]);
											}
											txt[6]=Math.round(Math.random()*100);
											s=txt[2]/txt[4]*txt[6];
										}
										while(txt[0]-s<0)
										{
											txt[0]=Math.round(Math.random()*(100));
										}
										s=txt[0]-s;
									}
									if(txt[1]=="+")
									{
										s=s+txt[0];
									}
								}
								if(txt[5]=="÷")
								{
									while(s%txt[6]!=0||txt[6]==0)
									{
										txt[6]=Math.round(Math.random()*100);
									}
									s=s/txt[6];
									if(txt[1]=="-")
									{
										while(txt[0]-s<0)
										{
											txt[0]=Math.round(Math.random()*100);
										}
										s=txt[0]-s;
									}
									if(txt[1]=="+")
									{
										s=s+txt[0];
									}
								}
							}
						}
					}
				}
				if(txt[1]=="*"||txt[1]=="÷")
				{
					if(txt[1]=="*")
					{
						s=s+txt[0]*txt[2];
						if(txt[3]=="-"||txt[5]=="+")
						{
							if(txt[5]=="-"||txt[5]=="+")
							{
								if(txt[3]=="-")
								{
									while(s-txt[4]<0)
									{
										txt[4]=Math.round(Math.random()*s);
									}
									s=s-txt[4];
									if(txt[5]=="-")
									{
										while(s-txt[6]<0)
										{
											txt[6]=Math.round(Math.random()*s);
										}
										s=s-txt[6];
									}
									if(txt[5]=="+")
									{
										s=s+txt[6];
									}
								}
								if(txt[3]=="+")
								{
									s=s+txt[4];
									if(txt[5]=="-")
									{
										while(s-txt[6]<0)
										{
											txt[6]=Math.round(Math.random()*s);
										}
										s=s-txt[6];
									}
									if(txt[5]=="+")
									{
										s=s+txt[6];
									}
								}
							}
							if(txt[5]=="*"||txt[5]=="÷")
							{
								if(txt[5]=="*")
								{
									var x;
									x=txt[4]*txt[6];
									if(txt[3]=="-")
									{
										while(s-x<0)
										{
											txt[6]=Math.round(Math.random()*100);
											txt[4]=Math.round(Math.random()*100);
											x=txt[4]*txt[6];
										}
										s=s-x;
									}
									if(txt[3]=="+")
									{
										s=s+x;
									}
								}
								if(txt[5]=="÷")
								{
									var y;
									while(txt[4]%txt[6]!=0||txt[6]==0)
									{
										txt[4]=Math.round(Math.random()*100);
										txt[6]=Math.round(Math.random()*100);
									}
									y=txt[4]/txt[6];
									if(txt[3]=="-")
									{
										while(s-y<0)
										{
											txt[4]=Math.round(Math.random()*100);
											while(txt[4]%txt[6]!=0)
											{
												txt[6]=Math.round(Math.random()*4);
											}
											y=txt[4]/txt[6];
										}
										s=s-y;
									}
									if(txt[3]=="+")
									{
										s=s+y;
									}
								}
							}
						}
						if(txt[3]=="*"||txt[5]=="÷")
						{
							if(txt[3]=="*")
							{
								s=s*txt[4];
								if(txt[5]=="-")
								{
									while(s-txt[6]<0)
									{
										txt[6]=Math.round(Math.random()*s);
									}
									s=s-txt[6];
								}
								if(txt[5]=="+")
								{
									s=s+txt[6];
								}
								if(txt[5]=="*")
								{
									s=s*txt[6];
								}
								if(txt[5]=="÷")
								{
									while(s%txt[6]!=0||txt[6]==0)
									{
										txt[6]=Math.round(Math.random()*100);
									}
									s=s/txt[6];
								}
							}
							if(txt[3]=="÷")
							{
								while(s%txt[4]!=0||txt[4]==0)
								{
									txt[4]=Math.round(Math.random()*100);
								}
								s=s/txt[4];
								if(txt[5]=="-")
								{
									while(s-txt[6]<0)
									{
										txt[6]=Math.round(Math.random()*100);
									}
									s=s-txt[6];
								}
								if(txt[5]=="+")
								{
									s=s+txt[6];
								}
								if(txt[5]=="*")
								{
									s=s*txt[6];
								}
								if(txt[5]=="÷")
								{
									while(s%txt[6]!=0||txt[6]==0)
									{
										txt[6]=Math.round(Math.random()*s);
									}
									s=s/txt[6];
								}
							}
						}
					}
					if(txt[1]=="÷")
					{
						while(txt[0]%txt[2]!=0||txt[2]==0)
						{
							txt[2]=Math.round(Math.random()*txt[0]);
						}
						s=s+txt[0]/txt[2];
						if(txt[3]=="-"||txt[3]=="+")
						{
							if(txt[5]=="-"||txt[5]=="+")
							{
								if(txt[3]=="-")
								{
									while(s-txt[4]<0)
									{
										txt[4]=Math.round(Math.random()*100);
									}
									s=s-txt[4];
									if(txt[5]=="-")
									{
										while(s-txt[6]<0)
										{
											txt[6]=Math.round(Math.random()*100);
										}
										s=s-txt[6];
									}
									if(txt[5]=="+")
									{
										s=s+txt[6];
									}
								}
								if(txt[3]=="+")
								{
									s=s+txt[4];
									if(txt[5]=="-")
									{
										while(s-txt[6]<0)
										{
											txt[6]=Math.round(Math.random()*100);
										}
										s=s-txt[6];
									}
									if(txt[5]=="+")
									{
										s=s+txt[6];
									}
								}
							}
							if(txt[5]=="*"||txt[5]=="÷")
							{
								if(txt[5]=="*")
								{
									var x;
									x=txt[4]*txt[6];
									if(txt[3]=="-")
									{
										while(s-x<0)
										{
											txt[6]=Math.round(Math.random()*100);
											txt[4]=Math.round(Math.random()*100);
											x=txt[4]*txt[6];
										}
										s=s-x;
									}
									if(txt[3]=="+")
									{
										s=s+x;
									}
								}
								if(txt[5]=="÷")
								{
									var y;
									while(txt[4]%txt[6]!=0||txt[6]==0)
									{
										txt[4]=Math.round(Math.random()*100);
										txt[6]=Math.round(Math.random()*100);
									}
									y=txt[4]/txt[6];
									if(txt[3]=="-")
									{
										while(s-y<0)
										{
											txt[4]=Math.round(Math.random()*100);
											while(txt[4]%txt[6]!=0||txt[6]==0)
											{
												txt[6]=Math.round(Math.random()*100);
											}
											y=txt[4]/txt[6];
										}
										s=s-y;
									}
									if(txt[3]=="+")
									{
										s=s+y;
									}
								}
							}
						}
						if(txt[3]=="*"||txt[3]=="÷")
						{
							if(txt[3]=="*")
							{
								s=s*txt[4];
								if(txt[5]=="-")
								{
									while(s-txt[6]<0)
									{
										txt[6]=Math.round(Math.random()*s);
									}
									s=s-txt[6];
								}
								if(txt[5]=="+")
								{
									s=s+txt[6];
								}
								if(txt[5]=="*")
								{
									s=s*txt[6];
								}
								if(txt[5]=="÷")
								{
									while(s%txt[6]!=0||txt[6]==0)
									{
										txt[6]=Math.round(Math.random()*s);
									}
									s=s/txt[6];
								}
							}
							if(txt[3]=="÷")
							{
								while(s%txt[4]!=0||txt[4]==0)
								{
									txt[4]=Math.round(Math.random()*s);
								}
								s=s/txt[4];
								if(txt[5]=="-")
								{
									while(s-txt[6]<0)
									{
										txt[6]=Math.round(Math.random()*s);
									}
									s=s-txt[6];
								}
								if(txt[5]=="+")
								{
									s=s+txt[6];
								}
								if(txt[5]=="*")
								{
									s=s*txt[6];
								}
								if(txt[5]=="÷")
								{
									while(s%txt[6]!=0||txt[6]==0)
									{
										txt[6]=Math.round(Math.random()*s);
									}
									s=s/txt[6];
								}
							}
						}
					}
				}
				$("#wb").append("<p>"+txt[0]+txt[1]+txt[2]+txt[3]+txt[4]+txt[5]+txt[6]+"="+s+"\r</p>");
			}
		}
	})
		
	document.querySelector('#save').addEventListener('click', saveFile);

       function fakeClick(obj) { 
       　　var ev = document.createEvent("MouseEvents");
　　　　ev.initMouseEvent("click", true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
　　　　obj.dispatchEvent(ev);
　　}

　　function exportRaw(name, data) {
　　　　var urlObject = window.URL || window.webkitURL || window;
　　　　var export_blob = new Blob([data]);
　　　　var save_link = document.createElementNS("http://www.w3.org/1999/xhtml", "a")
　　　　save_link.href = urlObject.createObjectURL(export_blob);
　　　　save_link.download = name;
　　　　fakeClick(save_link);
　　}

　　function saveFile(){
　　　　var inValue  = $("#wb p").text();
　　　　exportRaw('test.txt',inValue);
　　}
})