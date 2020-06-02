package simpledb;

import java.io.Serializable;
import java.util.*;

/**
 * TupleDesc describes the schema of a tuple.
 * 用于描述一个元组的模式
 */
public class TupleDesc implements Serializable {

	//字段数组
	private ArrayList<TDItem> TDList;

	
    /**
     * A help class to facilitate organizing the information of each field
     	单个字段的信息
     * */
    public static class TDItem implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * The type of the field
         * 字段类型
         * */
        public final Type fieldType;
        
        /**
         * The name of the field
         * 字段名可以是空
         * */
        public final String fieldName;

        //构造
        public TDItem(Type t, String n) {
            this.fieldName = n;
            this.fieldType = t;
        }

        //字段名（字段类型）
        public String toString() {
            return fieldName + "(" + fieldType + ")";
        }
    }

    /**
     * @return
     *        An iterator which iterates over all the field TDItems
     *        that are included in this TupleDesc
     *        构建一个包含元组所有字段的迭代器
     * */
    public Iterator<TDItem> iterator() {
        // some code goes here
        return TDList.iterator();
    }

    private static final long serialVersionUID = 1L;

    /**
     * 构造方法
     * 构建元组描述--就是字段集
     * Create a new TupleDesc with typeAr.length fields with fields of the
     * specified types, with associated named fields.
     * 
     * @param typeAr
     * 字段类型名数组
     *            array specifying the number of and types of fields in this
     *            TupleDesc. It must contain at least one entry.
     *            
     * @param fieldAr
     * 字段名数组
     *            array specifying the names of the fields. Note that names may
     *            be null.
     */
    public TupleDesc(Type[] typeAr, String[] fieldAr) {
        // some code goes here
    	TDList=new ArrayList<TDItem>();
    	for(int i=0;i<typeAr.length;i++) {
    		TDItem temp= new TDItem(typeAr[i],fieldAr[i]);
    		TDList.add(temp);
    	}
    }

    /**
     * 构造无名字段也可
     * Constructor. Create a new tuple desc with typeAr.length fields with
     * fields of the specified types, with anonymous (unnamed) fields.
     * 
     * @param typeAr
     *            array specifying the number of and types of fields in this
     *            TupleDesc. It must contain at least one entry.
     */
    public TupleDesc(Type[] typeAr) {
        // some code goes here
    	TDList=new ArrayList<TDItem>();
    	for(int i=0;i<typeAr.length;i++) {
    		TDItem temp= new TDItem(typeAr[i],"");
    		TDList.add(temp);
    	}
    }

    /**
     * 返回字段数
     * @return the number of fields in this TupleDesc
     */
    public int numFields() {
        // some code goes here
        return TDList.size();
    }

    /**
     * 获得第i个字段名（可能没有）
     * Gets the (possibly null) field name of the ith field of this TupleDesc.
     * 
     * @param i
     *            index of the field name to return. It must be a valid index.
     * @return the name of the ith field
     * @throws NoSuchElementException
     *             if i is not a valid field reference.
     */
    public String getFieldName(int i) throws NoSuchElementException {
        // some code goes here
    	if(i<0||i>TDList.size()-1) {
    		throw new NoSuchElementException("没有第"+i+"号字段！！");
    	}
        return TDList.get(i).fieldName;
    }

    /**
     * 获得第i个字段类型（可能没有）
     * Gets the type of the ith field of this TupleDesc.
     * 
     * @param i
     *            The index of the field to get the type of. It must be a valid
     *            index.
     * @return the type of the ith field
     * @throws NoSuchElementException
     *             if i is not a valid field reference.
     */
    public Type getFieldType(int i) throws NoSuchElementException {
        // some code goes here
    	if(i<0||i>TDList.size()-1) {
    		throw new NoSuchElementException("没有第"+i+"号字段！！");
    	}
        return TDList.get(i).fieldType;
    }

    /**
     * 字段名检索第几个（可能没有）
     * Find the index of the field with a given name.
     * No match if name is null.
     * 
     * @param name
     *            name of the field.
     * @return the index of the field that is first to have the given name.
     * @throws NoSuchElementException
     *             if no field with a matching name is found.
     */
    public int fieldNameToIndex(String name) throws NoSuchElementException {
        // some code goes here
    	for(int i=0;i<TDList.size();i++) {
    		if(TDList.get(i).fieldName.equals(name)){
    			return i;
    		}
    	}
        throw new NoSuchElementException("没找到字段"+name);
    }

    /**
     * 获得size
     * @return The size (in bytes) of tuples corresponding to this TupleDesc.
     *         Note that tuples from a given TupleDesc are of a fixed size.
     * @see Type#getSizeInBytes
     */
    
    public int getSizeInBytes() {
        // some code goes here
    	int res=0;
    	for(int i=0;i<TDList.size();i++) {
    		res+=(TDList.get(i).fieldType).getSizeInBytes();
    		
    	}
        return res;
    }

    /**
     * merge两个模式
     * Merge two TupleDescs into one, with td1.numFields + td2.numFields fields,
     * with the first td1.numFields coming from td1 and the remaining from td2.
     * 
     * @param td1
     *            The TupleDesc with the first fields of the new TupleDesc
     * @param td2
     *            The TupleDesc with the last fields of the TupleDesc
     * @return the new TupleDesc
     */
    public static TupleDesc merge(TupleDesc td1, TupleDesc td2) {
        // some code goes here
    	Type[] tlist=new Type[td1.numFields()+td2.numFields()];
    	String[] slist=new String[td1.numFields()+td2.numFields()];
    	int pos=0;
    	for(int i=0;i<td1.numFields();i++) {
    		tlist[pos]=td1.getFieldType(i);
    		slist[pos]=td1.getFieldName(i);
    		pos++;
    	}
    	for(int j=0;j<td2.numFields();j++) {
    		tlist[pos]=td2.getFieldType(j);
    		slist[pos]=td2.getFieldName(j);
    		pos++;
    	}
        return new TupleDesc(tlist,slist);
    }

    /**
     * 判等，字段数相同，各字段类型相同即相等
     * Compares the specified object with this TupleDesc for equality. Two
     * TupleDescs are considered equal if they have the same number of items
     * and if the i-th type in this TupleDesc is equal to the i-th type in o
     * for every i. It does not matter if the field names are equal.
     * 
     * @param o
     *            the Object to be compared for equality with this TupleDesc.
     * @return true if the object is equal to this TupleDesc.
     */
    public boolean equals(Object o) {
        // some code goes here
    	if(o instanceof TupleDesc) {
    		TupleDesc temp=(TupleDesc)o;
    		if(temp.getSizeInBytes()==this.getSizeInBytes()){
    			for(int i=0;i<temp.numFields()-1;i++) {
    				if(!this.getFieldType(i).equals(temp.getFieldType(i))) {
    					return false;
    				}
    			}
    			return true;
    		}else {
    			return false;
    		}
    	}else {
    		return false;
    	}
    }

    public int hashCode() {
        // If you want to use TupleDesc as keys for HashMap, implement this so
        // that equal objects have equals hashCode() results
        throw new UnsupportedOperationException("unimplemented");
    }

    /**
     * 模式字符串序列化
     * Returns a String describing this descriptor. It should be of the form
     * "fieldName[0](fieldType[0]), ..., fieldName[M](fieldType[M])", although
     * the exact format does not matter.
     * 
     * @return String describing this descriptor.
     */
    public String toString() {
        // some code goes here
    	String res="";
    	for(int i=0;i<this.numFields();i++) {
    		res+=this.getFieldName(i).toString()+"["+this.getFieldType(i)+"]/n";
    	}
        return res;
    }
}
