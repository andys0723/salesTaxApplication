package model;
/*****************************
 * 
 * @author tsai_yaoAn
 *
 *  Shopping baskets hold a list of item and allow user to iterate
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingBakets<T extends Item> implements Iterable<T> {

	private List<T> items;
	
	private ShoppingBakets() {
		this.items = new ArrayList<T>();
	}
	
	public static <T extends Item> ShoppingBakets<T>  getNewShoppingBaskets(){
		
		return new ShoppingBakets<T>();
		
	}

	public void addElement(T item){
		this.items.add(item);
	}
	
	@Override
	public Iterator<T> iterator() {
        
		Iterator<T> implentmentedIterator = new Iterator<T>() {

	        private Iterator<T> iterator = items.iterator();			
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public T next() {
				return iterator.next();
			}

			@Override
			public void remove() {
				iterator.remove();				
			}
			
		};
		
		
		return implentmentedIterator;
	}
}
