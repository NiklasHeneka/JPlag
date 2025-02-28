/**
 * Comparison model used by the Comparison Table in Overview. Only the needed attributes to display are included.
 * For full comparison model see Comparison.ts
 * @see Comparison
 * @property sortingPlace - Where the comparison is placed in the list sorted by the current metric
 * @property id - Index of the comparison in the sorted and filtered list
 * @property firstSubmissionId - Id of the first submission
 * @property secondSubmissionId - Id of the second submission
 * @property similarity - Similarity of the two submissions
 */
export type ComparisonListElement = {
  sortingPlace: number
  id: number
  firstSubmissionId: string
  secondSubmissionId: string
  averageSimilarity: number
  maximumSimilarity: number
}
