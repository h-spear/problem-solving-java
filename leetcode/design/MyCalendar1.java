// https://leetcode.com/problems/my-calendar-i/

package leetcode.design;

import java.util.*;

public class MyCalendar1 {

    class MyCalendar {

        private List<Pair> pairs = new ArrayList<>();

        public MyCalendar() {

        }

        public boolean book(int start, int end) {
            for (Pair p: pairs) {
                if (start < p.e && end > p.s) {
                    return false;
                }
            }
            pairs.add(new Pair(start, end));
            return true;
        }

        class Pair {
            int s, e;

            Pair(int s, int e) {
                this.s = s;
                this.e = e;
            }
        }
    }

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
}