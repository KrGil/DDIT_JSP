package kr.or.ddit.instance;

public class StringCheck {
   public static void main(String[] args) {
      String s1 = "test";
      String s2 = "test";
      String s3 = new String("test");
      String s4 = new String("test");
      
      System.out.println(s1.equals(s2));  // true
      System.out.println(s1==s2);  // false -- true
      System.out.println(s1.equals(s3));  // false -- true
      System.out.println(s1==s3);  // false
      System.out.println(s3==s4);  // false
      System.out.println(s3.intern()==s4.intern());
      
      TestVO vo1 = new TestVO();
      vo1.setInner("값");
      TestVO vo2 = new TestVO();
      vo2.setInner("값");
      System.out.printf("vo1's hashcode : %s\n", vo1.hashCode());
      System.out.printf("vo2's hashcode : %s\n", vo2.hashCode());
      System.out.println(vo1==vo2);  // 주소 비교
      System.out.println(vo1.equals(vo2));  // 주소 or 값 비교
      
//      method();
   }
   
   public static void method() {
//      String str = "constant";
      StringBuffer sb = new StringBuffer("buffer");
      for(int count=1; count<=Integer.MAX_VALUE; count++) {
//         str += count + "번째 반복";
         sb.append(count + "번째 반복");
         System.out.println(sb.hashCode());  // hashCode는 주소가 아니라 값이 가진 key다.
      }
   }
   
   public static class TestVO{
      private String inner;

      public String getInner() {
         return inner;
      }

      public void setInner(String inner) {
         this.inner = inner;
      }

      @Override
      public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + ((inner == null) ? 0 : inner.hashCode());
         return result;
      }

      @Override
      public boolean equals(Object obj) {
         if (this == obj)
            return true;
         if (obj == null)
            return false;
         if (getClass() != obj.getClass())
            return false;
         TestVO other = (TestVO) obj;
         if (inner == null) {
            if (other.inner != null)
               return false;
         } else if (!inner.equals(other.inner))
            return false;
         return true;
      }
      
   }
}