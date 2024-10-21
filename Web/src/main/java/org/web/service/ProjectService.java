package org.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.controllers.ProjectsController;
import org.web.entity.Person;
import org.web.entity.Project;
import org.web.implDTO.PersonImpl;
import org.web.implDTO.ProjectImpl;

import java.util.ArrayList;
import java.util.LinkedList;

@Service
public class ProjectService {
    @Autowired
    PersonImpl pers;

    @Autowired
    ProjectImpl projectDTO;

    public ArrayList<Project> getAllProjects(){
        ArrayList<Project> res = new ArrayList<>();

        /*ArrayList<Person> people = new ArrayList<>();

        Person person = new Person("eren", null, 20, null, "links", "assets/ava_name.jpg");
        people.add(person);
        Person person1 = new Person("evg", null, 20, null, "lis", "assets/ava_name.jpg");
        people.add(person1);
        pers.save(person);
        pers.save(person1);

        for (int i = 0; i < 20; i++) {
            Project s = new Project("public/assets/media/video" + i + ".webm", "Крутой проект" + i, "The Cool Project" + i, "Круты праект" + i, "sub", "image_type", "image_links", "design", people, "https://project.com/" + i, "des", "text");
            Project s1 = new Project("public/assets/media/video" + i + ".webm", "Крутой проект" + i, "The Cool Project" + i, "Круты праект" + i, "sub", "image_type", "image_links", "web", people, "https://project.com/" + i, "des", "text");

            projectDTO.save(s);
            projectDTO.save(s1);
        }*/

        try {
            res = (ArrayList<Project>) projectDTO.findAll();
            
        }
        catch (Exception e){
            System.out.println("Getting Error");
        }

        return res;
    }

    public Boolean addProject(Project project){
        try {
            projectDTO.save(project);
            return true;
        }
        catch (Exception e){
            System.out.println("Saving Error");
            return false;
        }
    }
}
