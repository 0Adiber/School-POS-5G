package at.kaindorf.schooldb.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Schule")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class School {
    @XmlElement(name = "Lehrer")
    private List<TeacherAdapter> teachers;
}
