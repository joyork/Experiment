package demostration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UserCenter {

	public static void main(String[] args) {
		Map<String, Object> maxcid = new HashMap<String, Object>();
		maxcid.put("1", 11);
		maxcid.put("2", 9);
		maxcid.put("3", 21);
		maxcid.put("4", 7);
		
		Iterator<String> iter = maxcid.keySet().iterator();
		List<Map<String, Object>> clist = new ArrayList<Map<String, Object>>();
		while (iter.hasNext()) {
			String c = iter.next();
			int i = 0;
			for (i = 0; i < clist.size(); i++) {
				if (((Integer) clist.get(i).get("count")) < ((Integer) maxcid.get(c))) {
					break;
				}
			}
			Map<String, Object> tmp = new HashMap<String, Object>();
			tmp.put("count", maxcid.get(c));
			tmp.put("categoryid", c);
			clist.add(i, tmp);
		}
	}
}
