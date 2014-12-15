package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;


public class YouxiImporter {
	
  public List<Product> pros = new ArrayList<Product>();

  public void addProduct(Product pro) {
    this.pros.add(pro);
  }

  /**
   * ����xml�ļ�
   * 
   * @param xmlFile
   * @return
   * @throws IOException
   * @throws SAXException
   * @throws DataAccessException
   * @throws ProductException
   */
  @SuppressWarnings("unchecked")
  public List<Product> parseXml(String xmlFile) throws IOException,
      SAXException{
    this.pros = new ArrayList<Product>();
    // 1�������йش��Ʒ���ݵ�XML�ļ�����ȡ����Ʒ�Ļ�����Ϣ
    Digester digester = new Digester();
    digester.push(this);
    digester.setValidating(false);

    digester.addObjectCreate("games/game", "game.Product");
    digester.addSetProperties("games/game");

    digester.addSetNext("games/game", "addProduct");

    File zolfile = new File(xmlFile);
    if (!zolfile.exists()) {
      System.out.println("�Ҳ���XML�����ļ�!");
      return null;
    }

    Object root = digester.parse(zolfile); // �����ļ�
    System.out.println("���������Ĳ�Ʒde����Ϊ��" + this.pros.size());
		Product special = pros.get(0);
		System.out.print(special.getGameName() + "  ");
		System.out.println(special.getGameWidth());
		System.out.println(special.getCreateDate());

    return this.pros;
  }
  

  public static void main(String[] args) {
	  String xmlFile = "d://games7k.xml";
	  YouxiImporter youxi = new YouxiImporter();
		try {
			List<Product> pros = youxi.parseXml(xmlFile);

			Product special = pros.get(1);
			System.out.print(special.getGameName() + "  ");
			System.out.println(special.getGameWidth());
			System.out.println(special.getCreateDate());

		} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
    } catch (SAXException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
    }
  }
	
}
