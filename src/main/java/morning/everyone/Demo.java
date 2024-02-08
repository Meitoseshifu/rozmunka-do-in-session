package morning.everyone;

import lombok.SneakyThrows;
import morning.everyone.entity.Address;
import morning.everyone.entity.Note;
import morning.everyone.entity.Person;
import morning.everyone.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.function.Consumer;

public class Demo {
    private static EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("default");

    @SneakyThrows
    public static void main(String[] args) {

        Address address = new Address();
        address.setCity("City");
        address.setNumber(13);
        address.setStreet("Street");

        User user = new User();
        user.setUsername("Jane Doe");
        user.setAddress(address);

        doInSession(entityManager -> entityManager.persist(user));

    }

    // todo: 1. persist(newPerson) – persist new person with new note so the note is inserted due to cascade (SELECT – 0, INSERT – 2)
    /*public static void todo1() {
        doInSession(entityManager -> {
            Person person = new Person();
            person.setFirstName("Raz");
            person.setLastName("Dva");
            person.setEmail("razdva@mail.com");
            entityManager.persist(person);

            Note note = new Note();
            note.setBody("todo1");
            note.setPerson(person);

            entityManager.persist(note);
        });
    }

    // todo: 2. persist(newNote) – persist new note linked to existing person (SELECT – 1, INSERT – 1)
    public static void todo2() {
        doInSession(entityManager -> {
            Person person1 = entityManager.find(Person.class, 55L);

            Note note = new Note();
            note.setBody("todo2");
            note.setPerson(person1);
            entityManager.persist(note);
        });
    }

    // todo: 3. no persist – create new note and add it to the existing person (SELECT – 1, INSERT – 1)
    public static void todo3() {
        doInSession(entityManager -> {
            Person person = new Person();
            person.setFirstName("P'9tb");
            person.setLastName("Shistb");
            person.setEmail("p9tbshistb@mail.com");

            Note note = new Note();
            note.setBody("todo3");
            note.setPerson(person);
        });
    }

    // todo: 4. * persist new note by person id without loading person to the session (SELECT – 0, INSERT – 1)
    public static void todo4() {
        doInSession(entityManager -> {
            Person person = new Person();
            person.setFirstName("Sim");
            person.setLastName("Visim");
            person.setEmail("simvisim@mail.com");

            Note note = new Note();
            note.setBody("todo4");
            note.setPerson(person);
        });
    }*/

    public static void doInSession(Consumer<EntityManager> entityManagerConsumer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManagerConsumer.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
