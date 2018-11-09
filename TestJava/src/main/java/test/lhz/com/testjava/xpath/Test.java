package test.lhz.com.testjava.xpath;


/**
 * 作者：测试开发栈
 * 链接：https://www.jianshu.com/p/5f9492e81198
 * 來源：简书
 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 */
public class Test {

    public static void main(String[] args) throws Exception {
        JsoupHelper.fecthByMap("http://www.jianshu.com/u/bf7b9c013c55", "//ul[@class='note-list']/li//a[@class='title']");
    }


}
