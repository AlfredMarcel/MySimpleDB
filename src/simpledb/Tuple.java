package simpledb;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Tuple maintains information about the contents of a tuple. Tuples have a
 * specified schema specified by a TupleDesc object and contain Field objects
 * with the data for each field.
 */
public class Tuple implements Serializable {

    private static final long serialVersionUID = 1L;

    //field 即抽象的不区分类型的 字段
    //一个tuple即是field的列表，也就是一行数据
    private Field[] fieldList;
    //元组描述TupleDesc，即是模式，即是表头
    private TupleDesc schema;
    //物理地址映射
    private RecordId record;
    
    /**
     * 根据元组描述（（字段名+类型）对象的数组）构建元组
     * Create a new tuple with the specified schema (type).
     * 
     * @param td
     *            the schema of this tuple. It must be a valid TupleDesc
     *            instance with at least one field.
     */
    public Tuple(TupleDesc td) {
    	this.fieldList=new Field[td.numFields()];
    	this.schema=td;
        // some code goes here
    }

    /**
     * 返回元组描述
     * @return The TupleDesc representing the schema of this tuple.
     */
    public TupleDesc getTupleDesc() {
        // some code goes here
        return this.schema;
    }

    /**
     * 返回物理地址（pageid+tupleid）
     * @return The RecordId representing the location of this tuple on disk.
     *         Should return RecordId that was set with setRecordId(). May be null.
     */
    public RecordId getRecordId() {
        // some code goes here
        return this.record;
    }

    /**
     * 给元组id设置新的物理地址
     * Set the RecordId information for this tuple.
     * 
     * @param rid
     *            the new RecordId for this tuple.
     */
    public void setRecordId(RecordId rid) {
        // some code goes here
    	this.record=rid;
    }

    /**
     * 将元组的第i字段改为f
     * Change the value of the ith field of this tuple.
     * 
     * @param i
     *            index of the field to change. It must be a valid index.
     * @param f
     *            new value for the field.
     */
    public void setField(int i, Field f) {
        // some code goes here
    	this.fieldList[i]=f;
    }

    /**
     * 获得元组的第i个字段
     * @return the value of the ith field, or null if it has not been set.
     * 
     * @param i
     *            field index to return. Must be a valid index.
     */
    public Field getField(int i) {
        // some code goes here
        return this.fieldList[i];
    }

    /**
     * 元组字符序列化
     * Returns the contents of this Tuple as a string. Note that to pass the
     * system tests, the format needs to be as follows:
     * 
     * column1\tcolumn2\tcolumn3\t...\tcolumnN\n
     * 
     * where \t is any whitespace, except newline, and \n is a newline
     */
    public String toString() {
        // some code goes here
    	String res="";
    	for(int i=0;i<this.fieldList.length;i++) {
    		res+=fieldList[i].toString();
    		res+="\t";
    	}
    	res+="\n";
    	return res;
        //还不知道这个throw啥这是
    	//throw new UnsupportedOperationException("Implement this");
    }
    
    /**
     * @return
     *        An iterator which iterates over all the fields of this tuple
     * */
    public Iterator<Field> fields()
    {
        // some code goes here
        return Arrays.asList(fieldList).iterator();
    }
    
    /**
     * 改表头
     * Reset the TupleDesc of this tuple
     * (Only affecting the TupleDesc, does not need to worry about fields inside the Tuple)
     * */
    public void resetTupleDesc(TupleDesc td)
    {
    	// some code goes here
    	this.schema=td;
        
    }
}
