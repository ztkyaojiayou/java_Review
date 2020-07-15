package java基础.反射;

public class Person {

	private int age ;
	public int age01 = 23 ;//用于演示平时的访问方法
	private String name;

	
	public Person(String name,int age) {//有参构造方法
		super();//若有父类构造方法（这里没有），则先调用，且这条语句是默认存在的，不管你写没写
		this.age = age;
		this.name = name;
		
		System.out.println("有参构造函数执行啦，有参对象建立啦----"+this.name+":"+this.age);
	
	}
	public Person() {//无参构造方法（但不是默认的）
		super();//若有父类构造方法（这里没有），则先调用，且这条语句是默认存在的，不管你写没写
		
		System.out.println("无参构造函数执行啦，无参对象建立啦");
		
		
	}
	
	public void show(){
		System.out.println("普通无参公有方法执行啦..."+ name+ age);
	}
	
	private void privateMethod(){
		System.out.println(" 普通无参私有方法执行啦---- ");
	}
	
	public void paramMethod(String str,int num){
		System.out.println("有参数的公有方法执行啦....."+str+":"+num);
		
	}
	public static void staticMethod(){
		System.out.println(" 静态static公有方法执行啦......");
	}
}
