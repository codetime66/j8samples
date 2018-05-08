package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 
 */
public class App {
   
    public int no_functional_sampleA(int start, int end, int acc){
        for(int i=start;i<=end;i++){
            acc += i;
        }
        return acc;
    }

    public int functional_sampleA(int start, int end, int acc){
        if(start > end){
            return acc;
        }
        return functional_sampleA(start+1, end, acc+start);
    }
    
    public List<Invoice> findInvoicesOld(List<Invoice> invoices, InvoicePredicate p){
        List<Invoice> result = new ArrayList<>();
        for(Invoice inv:invoices){
            if(p.test(inv)){
                result.add(inv);
            }
        }
        return result;
    }

    public List<Invoice> findInvoices(List<Invoice> invoices, Predicate<Invoice> p){
        List<Invoice> result = new ArrayList<>();
        for(Invoice inv:invoices){
            if(p.test(inv)){
                result.add(inv);
            }
        }
        return result;
    }
    
    public Invoice createInvoice(double amount, String customer){
        Invoice invoice = new Invoice();
        invoice.setAmount(amount);
        invoice.setCustomer(customer);
        return invoice;        
    }
    
    public static void main(String[] args) {
        System.out.println("Sample");
        App app = new App();
        System.out.println("sampleB:acc="+app.no_functional_sampleA(1, 10, 0));
        System.out.println("sampleB:acc="+app.functional_sampleA(1, 10, 0));
        //
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(app.createInvoice(10_000, "A"));
        invoices.add(app.createInvoice(10_000, "B"));
        invoices.add(app.createInvoice(10_000, "B"));
        invoices.add(app.createInvoice(10_500, "A"));
        invoices.add(app.createInvoice(11_000, "A"));
        //
        //behavior parameterization
        List<Invoice> envoicesFromA_OLD = app.findInvoicesOld(invoices, inv -> inv.getAmount()>10_000 && "A".equals(inv.getCustomer()));        
        envoicesFromA_OLD.forEach(inv -> System.out.println(inv.getAmount()));
        
        List<Invoice> envoicesFromA = app.findInvoices(invoices, inv -> inv.getAmount()>10_000 && "A".equals(inv.getCustomer()));
        envoicesFromA.forEach(inv -> System.out.println(inv.getAmount()));
        //
        long count = invoices.stream()
                  .filter(inv -> "B".equals(inv.getCustomer()))
                  .count();
        System.out.println(count);
        //
        List lst = invoices.stream().map(inv -> inv.getAmount()).collect(Collectors.toList());
        lst.forEach(amount -> System.out.println(amount));
        //
        Invoice myInv = new Invoice();
        myInv.setAmount(11_000);
        myInv.setCustomer("B");
        myInv.setPayment("C");
        String type = Optional.ofNullable(myInv).map(pay -> pay.getPayment()).map(tp -> tp.getType()).orElse("Z");
        System.out.println(type);                
        boolean b_type = Optional.ofNullable(myInv).map(pay -> pay.getPayment()).map(tp -> tp.getType()).isPresent();
        System.out.println(b_type);                        
        
        Invoice myInv2 = new Invoice();
        myInv2.setAmount(12_000);
        myInv2.setCustomer("A");
        String type2 = Optional.ofNullable(myInv2).map(pay -> pay.getPayment()).map(tp -> tp.getType()).orElse("Z");
        System.out.println(type2);                
        boolean b_type2 = Optional.ofNullable(myInv2).map(pay -> pay.getPayment()).map(tp -> tp.getType()).isPresent();
        System.out.println(b_type2);                        
        
        Invoice myInv3 = null;
        String type3 = Optional.ofNullable(myInv3).map(pay -> pay.getPayment()).map(tp -> tp.getType()).orElse("Z");
        System.out.println(type3);                
        boolean b_type3 = Optional.ofNullable(myInv3).map(pay -> pay.getPayment()).map(tp -> tp.getType()).isPresent();
        System.out.println(b_type3);                
        boolean b_type3_ = Optional.ofNullable(myInv3).map(Invoice::getPayment).map(Payment::getType).isPresent();
        System.out.println(b_type3_);                
        //
        Function<Invoice, String> customer = Invoice::getCustomer;
        System.out.println(customer.apply(myInv));
        Function<Invoice, String> customer2 = (Invoice inv) -> inv.getCustomer();
        System.out.println(customer2.apply(myInv2));        
    }
    
}
