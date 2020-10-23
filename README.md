### Frameshift substitutions and the frameshift tolerability of the genetic code and the protein-coding genes

#### 1	Protein and coding DNA sequences 

All available reference CDSs in model organisms were retrieved from the UCSC Genome Database.

Ten thousand simulated CDSs (each with 500 sense codons) were generated by Recodon 1.6.0. 

All input CDS files are in fasta format. 

#### 2	Aligning and computing the pairwise similarities of the wild-type and frameshifts

Program Frameshift-Align.java batch translates all coding sequences in their three different reading frames, align the three translations, and compute their pairwise similarities. 

Each internal nonsense codon was translated into an amino acid according to the readthrough rules (X. Wang [1]). 

#### 3 Computational analysis of frameshift codon substitutions

Program Frameshift-CODON.java computes the average substitution scores for each kind of codon substitution using a scoring matrix, BLOSSUM62, PAM250, or GON250. 

#### 4	Computational analysis of alternative codon tables

Program RandomCodes.java produces random codon tables by changing AAs assigned to the sense codons and keeping all degenerative codons synonymous (Freeland and Hurst [3]). 

Program AlternativeCodes.java produces all (13824) alternative codon tables by permuting the nucleotide in each codon position independently (Itzkovitz and Alon [4]).

The sum of FSSs for each of the random or compatible genetic codes was computed, sorted ascendingly and compared with the natural genetic code. 

#### 5	Computational analysis of the usage of codon and codon pairs and their frameshift substitution scores

Program CodonUsage.java computes the usages of codons in the genomes; 

Program CODONPAIR.java computes the usages of codon pairs in the genomes and their average FSSs. 

The codons/codon pairs whose observed value is greater/smaller than their expected value were identified as over-/under-represented, and their weighted average FSSs were calculated for each genome.

The result of these calculations is a list of 4096 codon pairs with their corresponding FSSs, which is used to evaluate the frameshift tolerability of the codon pairs presented in a genome.

Please cite the following articles if you use these programs:

[1] Wang X, Wang X, Chen G, Zhang J, Liu Y, Yang C. 2015. The shiftability of protein coding genes: the genetic code was optimized for frameshift tolerating. PeerJ PrePrints 3:e806v1 https://doi.org/10.7287/peerj.preprints.806v1

[2] X. Wang et al., Frameshifts and wild-type protein sequences are always highly similar because the genetic code is optimal for frameshift tolerance
bioRxiv 067736; doi: https://doi.org/10.1101/067736

[3] Freeland, S.J. and L.D. Hurst, The genetic code is one in a million. J Mol Evol, 1998. 47(3): p. 238-48.

[4] Itzkovitz, S. and U. Alon, The genetic code is nearly optimal for allowing additional information within protein-coding sequences. Genome Res, 2007. 17(4): p. 405-12.

