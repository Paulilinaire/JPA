package demo;

import entity.manyToMany.Post;
import entity.manyToMany.Tag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Demo6 {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
    public static void main() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Post post = new Post();
        Post post1 = new Post();

        post.setTitle("Sleep walking");
        post1.setTitle("Run into a bear");

        Tag tag = new Tag();
        Tag tag1 = new Tag();

        tag.setName("awkward");
        tag1.setName("not cool");

        post.getTagList().add(tag);
        post.getTagList().add(tag1);
        post1.getTagList().add(tag);

        em.persist(post1); // on persiste soit Post soit Tag, les 2 étant liés, si tag est commit post le sera et vice versa
        em.persist(post);

        em.getTransaction().commit();

        Tag tag2 = em.find(Tag.class, 1L);
        Post post2 = em.find(Post.class, 1L);

        System.out.println(tag2);
        System.out.println(post2);

        em.close();
        emf.close();

    }
}
