/*
 */
package sample;

@FunctionalInterface
public interface InvoicePredicate {
   public boolean test(Invoice inv);    
}
