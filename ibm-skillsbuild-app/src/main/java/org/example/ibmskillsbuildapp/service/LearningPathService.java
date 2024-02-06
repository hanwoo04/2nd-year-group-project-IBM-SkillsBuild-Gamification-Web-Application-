package org.example.ibmskillsbuildapp.service;

import java.util.ArrayList;
import java.util.List;
import org.example.ibmskillsbuildapp.model.LearningPath;
import org.springframework.stereotype.Service;

// TODO: Temporary(?) class, assuming courses will be stored in a database, can be replaced by a repo

/**
 * This class provides the service to manage learning paths
 */
@Service
public class LearningPathService {

    private List<LearningPath> learningPaths;

    public LearningPathService() {
        learningPaths = new ArrayList<>();
        LearningPath path1 = new LearningPath("Path1");
        path1.addCourse("Course1", "AVAILABLE");
        path1.addCourse("Course2", "STARTED");
        path1.addCourse("Course3", "COMPLETED");

        LearningPath path2 = new LearningPath("Path2");
        path2.addCourse("Course4", "AVAILABLE");
        path2.addCourse("Course5", "AVAILABLE");
        path2.addCourse("Course6", "STARTED");

        learningPaths.add(path1);
        learningPaths.add(path2);
    }

    public List<LearningPath> getAllLearningPaths() {
        return learningPaths;
    }
}
