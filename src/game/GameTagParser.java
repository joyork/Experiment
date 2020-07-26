package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.cyberneko.html.parsers.DOMParser;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.DOMReader;
import org.xml.sax.InputSource;

public class GameTagParser {

	private static String url = "http://www.7k7k.com/flash/15574.htm";

	public static String getHtml(String tempurl, String code) {
		try {
			URL url = new URL(tempurl);

			// ͨ��head�����ж�ҳ��������
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("HEAD");
			System.out.println(conn.getContentLength() + "\t" + tempurl);

			// errorҳ�ĳ���Ϊ254
			if (conn.getContentLength() < 255) {
				return null;
			}
			int fnstart = tempurl.lastIndexOf("/");
			int fnend = tempurl.lastIndexOf(".");
			String prefix = "D://projects";
			String ext = ".html";
			String sep = "//";
			String fileName = tempurl.substring(fnstart + 1, fnend);
			String fullFile = null;

			// ����ҳ����·����С��3λ�����ļ���ֱ�Ӵ����Ŀ¼������������ļ���
			if (fileName.length() < 3) {
				fullFile = prefix + sep + fileName + ext;
			} else {
				String firstDir = fileName.substring(0, 1);
				String secondDir = fileName.substring(0, 2);

				File fdir = new File(prefix + sep + firstDir);
				if (!fdir.exists())
					fdir.mkdir();
				File sdir = new File(prefix + sep + firstDir + sep + secondDir);
				if (!sdir.exists())
					sdir.mkdir();

				fullFile = prefix + sep + firstDir + sep + secondDir + sep + fileName
				    + ext;
			}

			// ��url�����������ļ�
			File file = new File(fullFile);
			FileUtils.copyURLToFile(url, file);

			return fullFile;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param filename
	 */
	private static void parseFileInfo(String filename) {
		try {
			// ʹ��HTMLParserת��Ϊ��׼html�ļ�
			DOMParser parser = new DOMParser();
			parser.setProperty(
			    "http://cyberneko.org/html/properties/default-encoding", "utf-8");
			parser.setFeature("http://xml.org/sax/features/namespaces", false);
			parser.setFeature(
			    "http://cyberneko.org/html/features/scanner/notify-builtin-refs",
			    true);
			parser.setFeature(
			    "http://apache.org/xml/features/scanner/notify-char-refs", true);
			parser.setFeature(
			    "http://apache.org/xml/features/scanner/notify-builtin-refs", true);
			InputSource is = new InputSource(new FileInputStream(new File(filename)));
			parser.parse(is);

			org.w3c.dom.Document doc = parser.getDocument();

			// dom4j��reader��W3C��domת��Ϊdom4j��document
			DOMReader domReader = new DOMReader();
			Document document = domReader.read(doc);

			// ��ȡ��Ϸ����
			List<Node> nameNodes = document
			    .selectNodes("/HTML/BODY/DIV[3]/DIV[2]/DIV[2]/DIV[2]/DIV[2]/UL/LI[1]/SPAN");
			String gameName = nameNodes.get(0).getText();
			System.out.println(gameName);

			// ������Ϸid
			int fns = filename.lastIndexOf("/");
			int fne = filename.lastIndexOf(".");
			String outId = filename.substring(fns+1,fne);
			
			// ��ȡ��ǩ�б�
			List<Node> nl = document
			    .selectNodes("/HTML/BODY/DIV[3]/DIV[2]/DIV[2]/DIV[2]/DIV[2]/UL/LI[2]/descendant::A");
			List<String> tagList = new ArrayList<String>();
			for (Iterator iterator = nl.iterator(); iterator.hasNext();) {
				Node node = (Node) iterator.next();
				tagList.add(node.getText());
				System.out.println(node.getText());
			}
			
			// �������ݿ�����Ϣ
			updateGameTags(gameName, outId, tagList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void updateGameTags(String gameName, String outId, List<String> tagList){
		
	}

	public static void main(String[] args) {
		String filename = getHtml(url, "utf-8");
		System.out.println(filename);
		parseFileInfo(filename);
	}

}
