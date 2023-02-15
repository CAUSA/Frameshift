### Frameshift Tolerance of the Genetic Code and Protein-coding Genes

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

#### 4	Computational analysis of alternative genetic codes

***RandomCodes.java*** produces random codon tables by changing AAs assigned to the sense codons and keeping all degenerative codons synonymous (Freeland and Hurst [3]). 

***AlternativeCodes.java*** produces all (13824) alternative codon tables by permuting the nucleotide in each codon position independently (Itzkovitz and Alon [4]).

The sum of FSSs for each of the random or compatible genetic codes was computed, sorted ascendingly, and compared with the natural genetic code to evaluate its optimality of the frameshift tolerability. 

#### 5	Computational analysis of the usage of codon and codon pairs and their frameshift substitution scores

***CodonUsage.java*** computes the usages of codons in the genomes; 

***CodonPair.java*** computes the usages of codon pairs in the genomes;

The codons/codon pairs whose observed value is greater/smaller than their expected value was identified as over-/under-represented, and their weighted average FSSs were calculated for each genome.

The result of these calculations is a list of 4096 codon pairs with their corresponding FSSs, which is used to evaluate the frameshift tolerability of the codon pairs presented in a genome.

#### 6	Computational analysis of AA properties

    This program produces nt-permutes, aa-permutes, shuffle, and random genetic codes, calculate the mean square differences (MSm and MSf) for real or fake AA properties upon mismatching/frameshifting, the Pearson correlation coefficients between MSm and MSf, and the probability of mismatch/frameshift optimality (Pm and Pf) of the standard genetic code (SGC).

Usage: java -cp ./ MsGeneticCode <D> <C> <R> <W> <S> <E>

         <D> Distribution (1 = Real; 2 = Uniform; 3 = Normal):

         <C> Number of random codes: (100~1,000,000);

         <R> Number of repeats: (1~1,000);

         <W> Number of amino acid swaps for each random codes(1~1000);

         <S> the Start number of AA property: (1~599);

         <E> the ending number of AA property: (1~599);

         D=1: Real AA properties 

         D=2: Fake AA properties simulated by random numbers with uniform distribution.

         D=3: Fake AA properties simulated by random numbers with normal distribution.

First, compile the java program:

javac -cp ./ MsGeneticCode.java

Then, use the following commands to analysis AA properties:
         
nohup java -Xms256g -Xmx256g -cp ./ MsGeneticCode real 1000000 1 1 1 564 &
nohup java -Xms256g -Xmx256g -cp ./ MsGeneticCode uniform 1000000 1 1 1 564 &
nohup java -Xms256g -Xmx256g -cp ./ MsGeneticCode normal 1000000 1 1 1 564 &

For each AA property, we calculated: 
(1) the MSm and MSf values for each genetic code;
(2) the Pearson's correlation coefficient (R) between the MSm and MSf ; 
(3) the probability of the SGC's mismatch (Pm) or frameshift (Pf) optimality. 

Notes:
(1) The real AA property data are derived from the AAindex database 
          The real AA property data are available at the AAindex database (https://www.genome.jp/aaindex/).
          The real AA property data are saved in file ./Matrix/AAindex1.txt
          The real AA property data are read by the AAindex.java
          The fake AA property data are simulated by random numbers with uniform or normal distribution.
          
(2) MSm and MSf are the mismatch and frameshift MS of a genetic code, respectively. 
          As -1 and +1 frameshift MS are identical, only +1 frameshift MS is considered. 
          
(3) For each AA property:
         Pm/Pf is the probability of mismatch/frameshift optimality of the SGC; 
         Pm/Pf is given by the fraction of codes with MSm/MSf values lower than that of the SGC. 
          
####This program requires multiple CPUs and large memory
       This program is written in Java, accelerated by parallel computing, use as many CPUs as possible, and uses large memory (>100GB). 
          
       You may not use it in small computers with small memory, or it will take very long time.
          
####Please cite the following article if you use these programs:

[1] X. Wang et al., Frameshift and wild-type proteins are often highly similar because the genetic code and genomes were optimized for frameshift tolerance. BMC Genomics 23, 416 (2022).
