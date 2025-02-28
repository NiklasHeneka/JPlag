package de.jplag;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jplag.clustering.ClusteringFactory;
import de.jplag.exceptions.ExitException;
import de.jplag.exceptions.SubmissionException;
import de.jplag.options.JPlagOptions;
import de.jplag.reporting.reportobject.model.Version;
import de.jplag.strategy.ComparisonStrategy;
import de.jplag.strategy.ParallelComparisonStrategy;

/**
 * This class coordinates the whole errorConsumer flow.
 */
public class JPlag {
    private static final Logger logger = LoggerFactory.getLogger(JPlag.class);

    public static final Version JPLAG_VERSION = loadVersion();

    private static Version loadVersion() {
        ResourceBundle versionProperties = ResourceBundle.getBundle("de.jplag.version");
        String versionString = versionProperties.getString("version");
        Version currentVersion = Version.parseVersion(versionString);
        return currentVersion == null ? Version.DEVELOPMENT : currentVersion;
    }

    private final JPlagOptions options;

    /**
     * Creates and initializes a JPlag instance, parameterized by a set of options.
     * @deprecated in favor of static {@link #run(JPlagOptions)}.
     * @param options determines the parameterization.
     */
    @Deprecated(since = "4.3.0")
    public JPlag(JPlagOptions options) {
        this.options = options;
    }

    /**
     * Main procedure, executes the comparison of source code submissions.
     * @deprecated in favor of static {@link #run(JPlagOptions)}.
     * @return the results of the comparison, specifically the submissions whose similarity exceeds a set threshold.
     * @throws ExitException if JPlag exits preemptively.
     */
    @Deprecated(since = "4.3.0")
    public JPlagResult run() throws ExitException {
        return run(options);
    }

    /**
     * Main procedure, executes the comparison of source code submissions.
     * @param options determines the parameterization.
     * @return the results of the comparison, specifically the submissions whose similarity exceeds a set threshold.
     * @throws ExitException if JPlag exits preemptively.
     */
    public static JPlagResult run(JPlagOptions options) throws ExitException {
        GreedyStringTiling coreAlgorithm = new GreedyStringTiling(options);
        ComparisonStrategy comparisonStrategy = new ParallelComparisonStrategy(options, coreAlgorithm);
        // Parse and validate submissions.
        SubmissionSetBuilder builder = new SubmissionSetBuilder(options);
        SubmissionSet submissionSet = builder.buildSubmissionSet();
        int submissionCount = submissionSet.numberOfSubmissions();
        if (submissionCount < 2)
            throw new SubmissionException("Not enough valid submissions! (found " + submissionCount + " valid submissions)");

        // Compare valid submissions.
        JPlagResult result = comparisonStrategy.compareSubmissions(submissionSet);
        if (logger.isInfoEnabled())
            logger.info("Total time for comparing submissions: {}", TimeUtil.formatDuration(result.getDuration()));
        result.setClusteringResult(ClusteringFactory.getClusterings(result.getAllComparisons(), options.clusteringOptions()));

        logSkippedSubmissions(submissionSet, options);

        return result;
    }

    private static void logSkippedSubmissions(SubmissionSet submissionSet, JPlagOptions options) {
        List<Submission> skippedSubmissions = submissionSet.getInvalidSubmissions();
        if (!skippedSubmissions.isEmpty()) {
            logger.warn("{} submissions were skipped (see errors above): {}", skippedSubmissions.size(), skippedSubmissions);
            if (options.debugParser()) {
                logger.warn("Erroneous submissions were copied to {}", new File(JPlagOptions.ERROR_FOLDER).getAbsolutePath());
            }
        }
    }
}
