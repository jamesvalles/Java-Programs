package hw5;
public class MyHTClass {
    private String key; 
    private Integer val;

    public MyHTClass(String key, Integer val) {
        
        this.key = key;
        this.val = val;
    }
    
    public String getkey() { 
    	return this.key;
    	}
    
    @Override
    public String toString() {
        return "MyHTClass{" + "Key: '" + key + '\'' + ", Val: " + val +'}';
    }

    @Override
    public int hashCode() {
        int hash = key.hashCode();
        hash = 31 * hash + val.hashCode();
        return hash;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyHTClass that = (MyHTClass) o;

        if (!key.equals(that.key)) return false;
        return val.equals(that.val);
    }


 
}
