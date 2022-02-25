### Frameshift Substitution Scores and Frameshift Tolerability of the Genetic Code and Protein-coding Genes

#### 1	Simulation and translation of coding DNA sequences (CDSs) 

Reference CDSs in model organisms were retrieved from the NCBI or UCSC Genome Database.

***RandomCDSs.java*** generate random CDSs, each containing a given number of random sense codons. 

***Translation.java*** contains two functions, "translate" and "translateReadthrough", which are called by other programs to translate coding sequences (CDSs) into protein sequences. 

The "translate" function translate CDSs using the standard genetic code, and each internal nonsense codon (or premature termination codon, PTC) was translated into a stop signal (*). 

The "translateReadthrough" function translate CDSs using the standard genetic code, and each internal nonsense codon (or premature termination codon, PTC) was translated into an amino acid according to the readthrough rules (Wang [1-2]). 

All input and output CDS and protein sequence files are in fasta format. 

***ScoringMatrix.java*** which is called by other programs to read the scoring matrices. 
The programs read AA Substitution Score Matrices in ./Matrix/Matrices.txt (from the AAindex database) ;

#### 2	Aligning and computing the pairwise similarities of the wild-type and frameshifts

***Similarity.java*** batch translates (real or simulated random) protein-coding sequences (CDSs) in their three different reading frames and compute their pairwise similarities. Before calculating the pairwise similarities, the three translations may or may not be aligned.

***SubScore.java*** whose function is same to Similarity.java but outputs every FSS (for every site of each pair of wild-type and frameshift translations)
 
#### 3 Computational analysis of frameshift codon substitutions

***ShiftCodons.java*** computes the frameshift substitution scores (FSS) for each kind of codon substitution using a scoring matrix, BLOSSUM62, PAM250, or GON250. 

***ShiftCodonPair.java*** computes the FSSs for each kind of codon pairs using a scoring matrix, BLOSSUM62, PAM250, or GON250. (This program was removed in version 2.1.001, as it was integrated into CodonPair.java.)

#### 4	Computational analysis of alternative codon tables

***RandomCodes.java*** produces random codon tables by changing AAs assigned to the sense codons and keeping all degenerative codons synonymous (Freeland and Hurst [3]). 

***AlternativeCodes.java*** produces all (13824) alternative codon tables by permuting the nucleotide in each codon position independently (Itzkovitz and Alon [4]).

The sum of FSSs for each of the random or compatible genetic codes was computed, sorted ascendingly, and compared with the natural genetic code to evaluate its optimality of the frameshift tolerability. 

#### 5	Computational analysis of the usage of codon and codon pairs and their frameshift substitution scores

***CodonUsage.java*** computes the usages of codons in the genomes; 

***CodonPair.java*** computes the usages of codon pairs in the genomes;

The codons/codon pairs whose observed value is greater/smaller than their expected value was identified as over-/under-represented, and their weighted average FSSs were calculated for each genome.

The result of these calculations is a list of 4096 codon pairs with their corresponding FSSs, which is used to evaluate the frameshift tolerability of the codon pairs presented in a genome.

#### 6	Computational analysis of the AA properties in Random genetic codes (RGCs) and alternative genetic codes (AGCs)

(1) To analysis real AAindex in normal distribution, use the following two programs:

***AAindexRandomCodesFrameshiftCorrelation123.java*** Computational analysis of the AA properties in Random genetic codes (RGCs) ; 
***AAindexAlternativeCodesFrameshiftCorrelation123.java*** Computational analysis of the AA properties in alternative genetic codes (AGCs);
The programs read AA properties data in ./Matrix/aaindex1.txt (from the AAindex database) ;

(2) To analysis Pseudo AAindex in normal distribution, use the following two programs:

***PseudoAAindexRandomCodesFrameshiftCorrelation123.java*** Computational analysis of the Pseudo AA properties in alternative genetic codes (AGCs) ;
***PseudoAAindexAlternativeCodesFrameshiftCorrelation123.java*** Computational analysis of the Pseudo AA properties in Random genetic codes (RGCs) ;  

The programs read AA properties data in ./Matrix/FakeAAindex.txt (from the AAindex database) ;
To analysis Pseudo AAindex in normal distribution, copy ./Matrix/normalFakeAAindex.txt to ./Matrix/FakeAAindex.txt;
To analysis Pseudo AAindex in uniform distribution, copy ./Matrix/uniformFakeAAindex.txt to ./Matrix/FakeAAindex.txt;

MSm and MSf are the mismatch and frameshift MS of a genetic code, respectively. As -1 and +1 frameshift MS are equal, only +1 frameshift MS was considered. For each AA property, we calculated: (1) the MSm and MSf values for each genetic code; (2) the Pearson's correlation coefficient (R) between the MSm and MSf of RGCs or AGCs; (3) the probability of the SGC's mismatch (Pm) or frameshift (Pf) optimality. 

####Please cite the following articles if you use these programs:

[1] Wang X, Wang X, Chen G, Zhang J, Liu Y, Yang C. 2015. The shiftability of protein-coding genes: the genetic code was optimized for frameshift tolerating. PeerJ PrePrints 3:e806v1 https://doi.org/10.7287/peerj.preprints.806v1

[2] X. Wang et al., Frameshifts and wild-type protein sequences are highly similar because the genetic code and genomes were optimized for frameshift tolerance. bioRxiv 067736; doi: https://doi.org/10.1101/067736

[3] Freeland, S.J. and L.D. Hurst. The genetic code is one in a million. J Mol Evol, 1998. 47(3): p. 238-48.

[4] Itzkovitz, S. and U. Alon. The genetic code is nearly optimal for allowing additional information within protein-coding sequences. Genome Res, 2007. 17(4): p. 405-12.

