package hive;

import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;

public class Avg extends UDAF {
	public static class AvgState {
		private long mCount;
		private double mSum;
	}

	public static class AvgEvaluator implements UDAFEvaluator {
		AvgState state;

		public AvgEvaluator() {
			super();
			state = new AvgState();
			init();
		}

		/** * init���������ڹ��캯��������UDAF�ĳ�ʼ�� */
		public void init() {
			state.mSum = 0;
			state.mCount = 0;
		}

		/** * iterate���մ���Ĳ������������ڲ�����ת���䷵������Ϊboolean * * @param o * @return */
		public boolean iterate(Double o) {
			if (o != null) {
				state.mSum += o;
				state.mCount++;
			}
			return true;
		}

		/**
		 * * terminatePartial�޲�������Ϊiterate������ת�����󣬷�����ת���ݣ� *
		 * terminatePartial������hadoop��Combiner * * @return
		 */
		public AvgState terminatePartial() {
			// combiner
			return state.mCount == 0 ? null : state;
		}

		/**
		 * * merge����terminatePartial�ķ��ؽ������������merge�������䷵������Ϊboolean * * @param o
		 * * @return
		 */
		public boolean merge(AvgState o) {
			if (o != null) {
				state.mCount += o.mCount;
				state.mSum += o.mSum;
			}
			return true;
		}

		/** * terminate�������յľۼ�������� * * @return */
		public Double terminate() {
			return state.mCount == 0 ? null : Double.valueOf(state.mSum
					/ state.mCount);
		}
	}
}