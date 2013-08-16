package redis;

import java.util.*;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;


public class FollowRelation {
	private static Jedis jedis = new Jedis("220.181.8.125",6379);

	public static void main(String[] args) {
		FollowRelation fo = new FollowRelation();
		String ua = "usera";
		String ub = "userb";
		String uc = "userc";
		String ud = "userd";
		
		fo.follow(ua, ub);
		fo.follow(ua, uc);

		// 显示following 两种方式
		String fromkey = KeyUtils.getUserFoloKey(ua);
		System.out.println("ua following :"+jedis.zrange(fromkey,0,-1));
		System.out.println("++++++++++++++");
		for (String follower :fo.following(ua)) {
			System.out.println(follower); 
		}
		System.out.println("++++++++++++++");
				
		// 显示交集
		String dstkey = "uabinter";
		fo.follow(ub, uc);
		jedis.zinterstore(dstkey, 
				KeyUtils.getUserFansKey(ub),
				KeyUtils.getUserFansKey(uc));
		System.out.println("ub uc fans inter:"+jedis.zrange(dstkey, 0 ,-1)); 
	
		// 测试排序
		fo.follow(ua, ud);
		List<String> fols = fo.following(ua);
		String fs1 = fols.get(1);
		String fsend = fols.get(fols.size()-1);
		fols.set(1, fsend);
		fols.set(fols.size()-1, fs1);
		String[] folss = new String[fols.size()];
		for (int i = 0;i<fols.size();i++) {
			folss[i]=fols.get(i);
		}
		System.out.println("ua following pre:"+jedis.zrange(fromkey,0,-1));
		fo.reorderFollowing(ua, folss);
		System.out.println("ua following after:"+jedis.zrange(fromkey,0,-1));
		
		// 测试取消关注
		fo.unfollow(ua, uc);
		System.out.println("ua following :"+jedis.zrange(fromkey,0,-1));
		
		// 测试是否关注
		System.out.println("ua is following ub:"+fo.isFollowing(ua, ub));
		System.out.println("ub's fans include ua:"+fo.isFan(ub, ua));
		fo.unfollow(ua, ub);
		System.out.println("ua is following ub:"+fo.isFollowing(ua, ub));
		System.out.println("ub's fans include ua:"+fo.isFan(ub, ua));
		
		// 测试关注数粉丝数
		System.out.println("ua following num:"+ fo.followingNum(ua)); 
		System.out.println("ub followed by num:"+ fo.followedbyNum(ub)); 
		//清理现场
//		destroy();
	}

	public static void destroy() {
		Set<String> keys = KeyUtils.getTotalKeys();
		String[] keysArray = new String[keys.size()]; 
		keysArray = keys.toArray(keysArray);
		jedis.del(keysArray);
	}
	
	public boolean follow(String fromId , String toId){
		String fromkey = KeyUtils.getUserFoloKey(fromId);
		String tokey = KeyUtils.getUserFansKey(toId);
		jedis.zadd(fromkey, jedis.zcard(fromkey), toId);
		jedis.zadd(tokey, jedis.zcard(tokey), fromId);
		return true;
	}
	public boolean isFollowing(String fromId,String toId){
		String fromkey = KeyUtils.getUserFoloKey(fromId);
		Long rank = jedis.zrank(fromkey, toId);
		return rank!=null;
	}
	public boolean isFan(String fromId,String toId){
		String fromkey = KeyUtils.getUserFansKey(fromId);
		Long rank = jedis.zrank(fromkey, toId);
		return rank!=null;
	}
	public boolean unfollow(String fromId, String toId){
		String fromkey = KeyUtils.getUserFoloKey(fromId);
		String tokey = KeyUtils.getUserFansKey(toId);
		jedis.zrem(fromkey, toId);
		jedis.zrem(tokey, fromId);
		return true;
	}
	public int followingNum(String fromId){
		String fromkey = KeyUtils.getUserFoloKey(fromId);
		return jedis.zcard(fromkey).intValue();
	}
	public List<String> following(String fromId){
		String fromkey = KeyUtils.getUserFoloKey(fromId);
		Set<String> rs = jedis.zrange(fromkey, 0,-1);
		return new ArrayList<String>(rs); 
	}
	public int followedbyNum(String toId){
		String tokey = KeyUtils.getUserFansKey(toId);
		return jedis.zcard(tokey).intValue();
	}
	public List<String> followedby(String fromId){
		String fromkey = KeyUtils.getUserFansKey(fromId);
		Set<String> rs = jedis.zrange(fromkey, 0,-1);
		return new ArrayList<String>(rs); 
	}
	
	public boolean reorderFollowing(String fromId, String[] toIds){
		String fromkey = KeyUtils.getUserFoloKey(fromId);
		Transaction t = jedis.multi();
		t.del(fromkey);
		for(int i=0;i<toIds.length;i++){
			t.zadd(fromkey, i, toIds[i]);
		}
		t.exec();
		return true;
	}
	
}
