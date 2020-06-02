package simpledb;

import java.io.Serializable;

/**
 * A RecordId is a reference to a specific tuple on a specific page of a
 * specific table.
 */
public class RecordId implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private PageId pageId;
    private int tupleId;

    /**
     * 构造（pageid，元组id）
     * Creates a new RecordId referring to the specified PageId and tuple
     * number.
     * 
     * @param pid
     *            the pageid of the page on which the tuple resides
     * @param tupleno
     *            the tuple number within the page.
     */
    public RecordId(PageId pid, int tupleno) {
        // some code goes here
    	this.pageId=pid;
    	this.tupleId=tupleno;
    }

    /**
     * 返回元组id
     * @return the tuple number this RecordId references.
     */
    public int tupleno() {
        // some code goes here
        return tupleId;
    }

    /**
     * 获得pageID
     * @return the page id this RecordId references.
     */
    public PageId getPageId() {
        // some code goes here
        return pageId;
    }

    /**
     * 对于元组相同则equal
     * Two RecordId objects are considered equal if they represent the same
     * tuple.
     * 
     * @return True if this and o represent the same tuple
     */
    @Override
    public boolean equals(Object o) {
        // some code goes here
    	if(o instanceof RecordId) {
    		RecordId temp=(RecordId)o;
    		if(this.tupleno()==temp.tupleno()) {
    			return true;
    		}else {
    			return false;
    		}
    	}else {
    		throw new UnsupportedOperationException("implement this");
    	}
    }

    /**
     * 对于RecordId对象的hash函数
     * You should implement the hashCode() so that two equal RecordId instances
     * (with respect to equals()) have the same hashCode().
     * 
     * @return An int that is the same for equal RecordId objects.
     */
    @Override
    public int hashCode() {
        // some code goes here
    	return Integer.parseInt(String.valueOf(this.getPageId().hashCode())+String.valueOf(this.tupleno()));
    	//尚未理解
        //throw new UnsupportedOperationException("implement this");

    }

}
