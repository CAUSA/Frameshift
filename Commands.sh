The command lines to compile and execute the java programs:

# 1. Compile the java programs
javac -cp ./ Translation.java
javac -cp ./ CodonPair.java
javac -cp ./ CodonUsage.java
javac -cp ./ ShiftCodons.java
javac -cp ./ AlternativeCodes.java
javac -cp ./ RandomCodes.java
javac -cp ./ RandomCDSs.java
javac -cp ./ Similarity.java
javac -cp ./ SubScore.java
javac -cp ./ ScoringMatrix.java

# 1. Computes the frameshift substitution scores (FSS) for each kind of codon substitution using a scoring matrix, BLOSSUM62, PAM250, or GON250.
 java -cp ./ ShiftCodons -M=1 > ShiftCodons-1-Gon250.txt
 java -cp ./ ShiftCodons -M=2 > ShiftCodons-2-Blossum62.txt
 java -cp ./ ShiftCodons -M=3 > ShiftCodons-3-PAM250.txt
 
# 2. Produce random genetic codes and calculate their FSSs
# 2.1 Produce one million random genetic codes, calculate, and output their FSSs for each genetic code
java -cp ./ RandomCodes 1 1000000 1 Y >  RandomCodes-1Mx1-1-Gon250.txt &
java -cp ./ RandomCodes 2 1000000 1 Y >  RandomCodes-1Mx1-2-Blossum62.txt  &
java -cp ./ RandomCodes 3 1000000 1 Y >  RandomCodes-1Mx1-3-PAM250.txt &
java -cp ./ RandomCodes 4 1000000 1 Y >  RandomCodes-1Mx1-4-Gon120.txt &

# 2.2 Produce one million random genetic codes for 100 times, and calculate their average FSSs 
java -cp ./ RandomCodes 1 1000000 100 N >  RandomCodes-1Mx100-1-Gon250.txt &
java -cp ./ RandomCodes 2 1000000 100 N >  RandomCodes-1Mx100-2-Blossum62.txt &
java -cp ./ RandomCodes 3 1000000 100 N >  RandomCodes-1Mx100-3-PAM250.txt &
									    
# 3. Produce all 13824 alternative genetic codes and calculate their FSSs
 java -cp ./ AlternativeCodes 1 > AlternativeCodes-1-Gon250.txt &
 java -cp ./ AlternativeCodes 2 > AlternativeCodes-2-Blossum62.txt &
 java -cp ./ AlternativeCodes 3 > AlternativeCodes-3-PAM250.txt &
 java -cp ./ AlternativeCodes 4 > AlternativeCodes-4-Gon120.txt &

# 4. Produce random CDSs 
mkdir ./Simulated
java -cp ./ RandomCDSs ./Simulated 100000

# 5.  Calculate random Similarities of the random CDSs
# 5.1 Calculate frameshift Similarities of the random CDSs, aligningby Clustalw 
java -cp ./ Similarity ./Simulated Random-100000.CDS.fas Readthrough=Yes Random Clustalw 

# 5.2 Calculate frameshift Similarities of the random CDSs, aligning by MSA 
java -cp ./ Similarity ./Simulated Random-100000.CDS.fas Readthrough=Yes Random MSA 

# 5.3 Calculate frameshift Similarities of the random CDSs, aligningby FrameAlign 
java -cp ./ Similarity ./Simulated Random-100000.CDS.fas Readthrough=Yes Random FrameAlign 

# 6 Calculate frameshift Similarities of the random CDSs 
# 6.1 Calculate frameshift Similarities of the random CDSs, aligning by Clustalw 
java -cp ./ Similarity ./Simulated Random-100000.CDS.fas Readthrough=Yes Frameshift Clustalw 

# 6.2 Calculate frameshift Similarities of the random CDSs, aligning by MSA 
java -cp ./ Similarity ./Simulated Random-100000.CDS.fas Readthrough=Yes Frameshift MSA 

# 6.3 Calculate frameshift Similarities of the random CDSs, aligning by FrameAlign 
java -cp ./ Similarity ./Simulated Random-100000.CDS.fas Readthrough=Yes Frameshift FrameAlign 

# 7. Calculate frameshift Similarity of the real CDSs (E. coli for example)
# 7.1 download the reference CDSs from GenBank Genome Database
Ecoli_CDS.fas

# 7.2 move the reference CDSs to the destination directory 
mkdir ./Ecoli/
mv Ecoli_CDS.fas   ./Ecoli/          

# 7.3 Calculate the frameshift Similarity
java -cp ./ Similarity ./Ecoli/ Ecoli_CDS.fas Readthrough=Yes ToAlign=No

# 8. Computes the usage of codons and codon pairs(E. coli for example)
# 8.1 Computes the usage of codons and the FSSs for each codons
 java -cp ./ CodonUsage ./Ecoli/Ecoli_CDS.fas > ShiftCodonPair-1-Gon250.txt
 
# 8.2 Computes  the usage of codon pairs and the FSSs for each codon pairs
java -cp ./ CodonPair ./Ecoli/Ecoli_CDS.fas > ShiftCodonPair-1-Gon250.txt

