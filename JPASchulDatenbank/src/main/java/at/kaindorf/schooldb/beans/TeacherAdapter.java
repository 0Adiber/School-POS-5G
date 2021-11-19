package at.kaindorf.schooldb.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherAdapter {
    @XmlElement(name = "Kürzel")
    private String initials;
    @XmlElement(name = "Titel")
    private String title;
    @XmlElement(name = "Familienname")
    private String lastname;
    @XmlElement(name = "Vorname")
    private String firstname;
    @XmlElement(name = "Klasse")
    private String classname;
    @XmlElement(name = "Schüler")
    private int students;
    @XmlElement(name = "Raum")
    private String room;
}
