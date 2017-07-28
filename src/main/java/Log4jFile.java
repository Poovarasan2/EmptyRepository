import org.apache.log4j.Logger;

public class Log4jFile
{
	public static void msg(String msg,String name)
	{
		Logger log = Logger.getLogger(Log4jFile.class.getName());
		log.info(name+" account is "+msg);
	}
}