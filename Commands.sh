# 1. Compile the java programs

javac -cp ./ Translation.java
javac -cp ./ CodonPair.java
javac -cp ./ CodonUsage.java
javac -cp ./ FrameshiftCodon.java
javac -cp ./ FrameshiftCodonPair.java
javac -cp ./ AlternativeCodes.java
javac -cp ./ RandomCodes.java
			
javac -cp ./ RandomCDSs.java
javac -cp ./ RandomSimilarity.java
javac -cp ./ FrameshiftSimilarity.java

# download reference CDSs from GenBank or UCSC Genome Database
mv Ecoli_CDS.fas   ./Ecoli/           &
mv yeast_CDS.fas   ./Yeast/            &
mv nematode_CDS.fas  ./Nematode/      &
mv fruitfly_CDS.fas ./Fruitfly/        &
mv arabidopsis_CDS.fas ./Arabidopsis/  &
mv zebrafish_CDS.fas  ./Zebrafish/     &
mv xenopus_CDS.fas  ./Xenopus/        &
mv mouse_CDS.fas  ./Mouse/             &
mv pan_CDS.fas   ./PanTroglodytes/    &
mv human_CDS.fas  ./Human/             &

# 2. Produce random CDSs 
java -cp ./ RandomCDSs ./Simulated 100000

# 3. Calculate random Similarities of the random CDSs
java -cp ./ RandomSimilarity ./Simulated 100000  ToAlign=No

# 4. Calculate frameshift Similarities of the random CDSs
java -cp ./ FrameshiftSimilarity ./Simulated Random-100000.CDS.fas Readthrough=No ToAlign=No
java -cp ./ FrameshiftSimilarity ./Simulated Random-100000.CDS.fas Readthrough=Yes ToAlign=No

# 5. Calculate frameshift Similarity of real CDSs
java -cp ./ FrameshiftSimilarity ./Ecoli/ Ecoli_CDS.fas Readthrough=Yes ToAlign=No             &
java -cp ./ FrameshiftSimilarity ./Yeast/ yeast_CDS.fas Readthrough=Yes ToAlign=No             &
java -cp ./ FrameshiftSimilarity ./Nematode/ nematode_CDS.fas Readthrough=Yes ToAlign=No       &
java -cp ./ FrameshiftSimilarity ./Fruitfly/ fruitfly_CDS.fas Readthrough=Yes ToAlign=No       &
java -cp ./ FrameshiftSimilarity ./Arabidopsis/ arabidopsis_CDS.fas Readthrough=Yes ToAlign=No &
java -cp ./ FrameshiftSimilarity ./Zebrafish/ zebrafish_CDS.fas Readthrough=Yes ToAlign=No     &
java -cp ./ FrameshiftSimilarity ./Xenopus/ xenopus_CDS.fas Readthrough=Yes ToAlign=No         &
java -cp ./ FrameshiftSimilarity ./Mouse/ mouse_CDS.fas Readthrough=Yes ToAlign=No             &
java -cp ./ FrameshiftSimilarity ./PanTroglodytes/ pan_CDS.fas Readthrough=Yes ToAlign=No      &
java -cp ./ FrameshiftSimilarity ./Human/ human_CDS.fas Readthrough=Yes ToAlign=No             &

# 5. Get the result files	   
mv ./Simulated/*.Similarities.txt ./
mv ./Human/*.Similarities.txt ./
mv ./Ecoli/*.Similarities.txt ./
mv ./Yeast/*.Similarities.txt ./
mv ./Mouse/*.Similarities.txt ./
mv ./Zebrafish/*.Similarities.txt ./
mv ./Xenopus/*.Similarities.txt ./
mv ./PanTroglodytes/*.Similarities.txt ./
mv ./Nematode/*.Similarities.txt ./
mv ./Fruitfly/*.Similarities.txt ./
mv ./Arabidopsis/*.Similarities.txt ./


