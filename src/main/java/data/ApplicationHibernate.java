package data;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.Date;

public class ApplicationHibernate {

    //    public static void main(String[] args) {
    public static void main_(String[] args) {
        SessionFactory sessionFactory;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            User user = User.builder()
                    .age(20)
                    .birthDate(new Date())
                    .emailAddress("redlongcity@gmail.com")
                    .firstName("Vladimir")
                    .lastName("Kharchenko")
                    .lastUpdatedDate(new Date())
                    .addresses(Lists.newArrayList(Address.builder()
                            .addressLine1("addressLine1")
                            .addressLine2("addressLine2")
                            .city("City")
                            .build()))
                    .build();

            Credential credential = Credential.builder()
                    .password("password")
                    .credentialId(11L)
                    .user(user)
                    .build();

            user.setCredential(credential);

            session.save(credential);
            tx.commit();

            User userDb = (User) session.get(User.class, credential.getUser().getUserId());
            System.out.println("UserDb: " + userDb);

            tx = session.beginTransaction();

            Transaction transaction = Transaction.builder()
                    .amount(new BigDecimal("10000.00"))
                    .createdDate(new Date())
                    .lastUpdatedDate(new Date())
                    .createdBy("Bank_Employee")
                    .build();

            Account account = Account.builder()
                    .name("Vladimir")
                    .initialBalance(new BigDecimal("1.01"))
                    .currentBalance(new BigDecimal("100000000"))
                    .openDate(new Date())
                    .closeDate(new Date())
                    .lastUpdatedDate(new Date())
                    .transactions(Lists.newArrayList(transaction))
                    .build();

            transaction.setAccount(account);

            Budget budget = Budget.builder()
                    .period("PERIOD")
                    .goalAmount(new BigDecimal("100000"))
                    .transactions(Lists.newArrayList(transaction))
                    .build();

            session.save(account);
            session.save(budget);

            tx.commit();

            Transaction transactionDb = (Transaction) session.get(Transaction.class, transaction.getTransactionId());
            System.out.println(transactionDb);

            tx = session.beginTransaction();

            account.setUsers(Sets.newHashSet(user));
            session.update(account);

            tx.commit();

//            UserCredentialView view = (UserCredentialView) session.get(UserCredentialView.class, 1L);
//            System.out.println(view.getFirstName());
//            System.out.println(view.getLastName());

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
//            sessionFactory.close();
        }
    }

//    public static void main(String[] args) {
    public static void main__(String[] args) {
        SessionFactory sessionFactory;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            User user1 = User.builder()
                    .age(20)
                    .birthDate(new Date())
                    .emailAddress("redlongcity@gmail.com")
                    .firstName("Vladimir")
                    .lastName("Kharchenko")
                    .lastUpdatedDate(new Date())
                    .addresses(Lists.newArrayList(Address.builder()
                            .addressLine1("addressLine1")
                            .addressLine2("addressLine2")
                            .city("City")
                            .build()))
                    .build();
            User user2 = User.builder()
                    .age(20)
                    .birthDate(new Date())
                    .emailAddress("redlongcity@gmail.com")
                    .firstName("Vladimir2")
                    .lastName("Kharchenko")
                    .lastUpdatedDate(new Date())
                    .addresses(Lists.newArrayList(Address.builder()
                            .addressLine1("addressLine1")
                            .addressLine2("addressLine2")
                            .city("City")
                            .build()))
                    .build();

            Account account1 = Account.builder()
                    .name("Vladimir")
                    .initialBalance(new BigDecimal("1.01"))
                    .currentBalance(new BigDecimal("100000000"))
                    .openDate(new Date())
                    .closeDate(new Date())
                    .lastUpdatedDate(new Date())
                    .transactions(Lists.newArrayList())
                    .build();

            Account account2 = Account.builder()
                    .name("Vladimir2")
                    .initialBalance(new BigDecimal("1.01"))
                    .currentBalance(new BigDecimal("100000000"))
                    .openDate(new Date())
                    .closeDate(new Date())
                    .lastUpdatedDate(new Date())
                    .transactions(Lists.newArrayList())
                    .build();

            account1.setUsers(Sets.newHashSet(user1, user2));
            account2.setUsers(Sets.newHashSet(user1, user2));

            user1.setAccounts(Sets.newHashSet(account1, account2));
            user2.setAccounts(Sets.newHashSet(account1, account2));

            session.save(account1);
            session.save(account2);

            tx.commit();

            Account accountDb = (Account) session.get(Account.class, account1.getAccountId());
            User userDb = (User) session.get(User.class, user1.getUserId());

            System.out.println("accountDb - users " + accountDb.getUsers());
            System.out.println("userDb - account " + userDb.getAccounts());

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
//            sessionFactory.close();
        }

    }

//    public static void main(String[] arg) {
    public static void main_Compoud_Keys(String[] arg) {
        SessionFactory sessionFactory = null;
        Session session = null;
        Session session2 = null;
        org.hibernate.Transaction tx = null;
        org.hibernate.Transaction tx2 = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Currency currency = new Currency();
            currency.setCountryName("United States");
            currency.setName("Dollar");
            currency.setSymbol("$");

            session.persist(currency);
            tx.commit();

            session2 = sessionFactory.openSession();
            tx2 = session2.beginTransaction();

            Currency dbCurrency = (Currency) session2.get(Currency.class,
                    new CurrencyId("Dollar", "United States"));
            System.out.println(dbCurrency.getName());

            tx2.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            if (tx2 != null) {
                tx2.rollback();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

//    public static void main(String[] arg) {
    public static void main_CompoudKey(String[] arg) {
        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;
        org.hibernate.Transaction tx2 = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Currency currency = new Currency();
            currency.setCountryName("United States");
            currency.setName("Dollar");
            currency.setSymbol("$");

            Market market = Market.builder()
                    .marketName("Varus")
                    .currency(currency)
                    .build();

            session.persist(market);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            if (tx2 != null) {
                tx2.rollback();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    public static void main_Compoud_Keys_Join_Table(String[] arg) {
        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;
        org.hibernate.Transaction tx2 = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Account account = Account.builder()
                    .name("Vladimir")
                    .initialBalance(new BigDecimal("1.01"))
                    .currentBalance(new BigDecimal("100000000"))
                    .openDate(new Date())
                    .closeDate(new Date())
                    .lastUpdatedDate(new Date())
                    .accountType(AccountType.LOAN)
                    .build();

            session.persist(account);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            if (tx2 != null) {
                tx2.rollback();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    public static void main_Bond_Stock(String[] arg) {
        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;
        org.hibernate.Transaction tx2 = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Stock stock = new Stock("name_", "issuer_", new Date(),
                    new BigDecimal("1.01"), new BigDecimal("2.02"));
            Bond bond = new Bond("name_", "issuer_", new Date(),
                    new BigDecimal("1.01"), new BigDecimal("2.02"), new Date());

            session.persist(stock);
            session.persist(bond);

            Stock stockDb = (Stock) session.get(Stock.class, 1L);
            Bond bondDb = (Bond) session.get(Bond.class, 1L);

//            System.out.println(stockDb.getStockId());
//            System.out.println(bondDb.getBondId());

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            if (tx2 != null) {
                tx2.rollback();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    public static void main(String[] arg) {
        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;
        org.hibernate.Transaction tx2 = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Portfolio portfolio = Portfolio.builder()
                    .name("name_")
                    .build();

            Stock stock = new Stock("name_", "issuer_", new Date(),
                    new BigDecimal("1.01"), new BigDecimal("2.02"));
            Bond bond = new Bond("name_", "issuer_", new Date(),
                    new BigDecimal("1.01"), new BigDecimal("2.02"), new Date());

            portfolio.setInvestements(Lists.newArrayList(stock, bond));
            stock.setPortfolio(portfolio);
            bond.setPortfolio(portfolio);

            session.persist(portfolio);
            session.persist(stock);
            session.persist(bond);

            tx.commit();


            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Stock stockDb = (Stock) session.get(Stock.class, 1L);
            System.out.println("STOCK_DB QNT: " + stockDb.getQuantity());

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            if (tx2 != null) {
                tx2.rollback();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
