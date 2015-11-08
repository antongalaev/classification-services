
require("quint")
require("stima")
require("survival")

all_data <- read.csv("logrank.csv")
stima_data <- read.csv("stima.csv")
quint_data <- read.csv("quint.csv")

#STIMA

stima1 <- stima::stima(stima_data, 10, first=2)

stima1
plot(stima1, digits=2)

#QUINT

formula1 <- Time~Rand1 | Sex+ImmunCat+CNS+Mediastinum+Age+Leuc+Leber+Milz
control1 <- quint::quint.control(maxl=10, B=2, dmin=0.1)
#control1 <- quint.control(maxl=5,Bootstrap=FALSE,dmin=0.1)

quint1<-quint::quint(formula1, data=quint_data, control=control1)
plot(quint1)


#Logrank tests

# STIMA - MePRED hypotheses

subgroup1 <- subset(all_data, Age >= 2.9, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# Chisq= 0.4  on 1 degrees of freedom, p= 0.548
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("DEXA", "MEPRED"), lty=1:2, title="Treatment Type:", bty="n")

subgroup2 <- subset(all_data, Age < 2.9 & Leuc >= 6.8, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup2)
# Chisq= 1.9  on 1 degrees of freedom, p= 0.165
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup2), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("DEXA", "MEPRED"), lty=1:2, title="Treatment Type:", bty="n")

subgroup3 <- subset(all_data, Milz < 2.8 & Leuc >= 2.2, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup3)
# Chisq= 0.1  on 1 degrees of freedom, p= 0.72
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup3), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("DEXA", "MEPRED"), lty=1:2, title="Treatment Type:", bty="n")

subgroup9 <- subset(all_data, Milz >= 2.8 & Age < 3.3 & Leuc < 16.1, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup9)
# Chisq= 0  on 1 degrees of freedom, p= 0.95
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup9), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("DEXA", "MEPRED"), lty=1:2, title="Treatment Type:", bty="n")

subgroup10 <- subset(all_data, Milz >= 2.8 & Age >= 6 & Age < 11.5, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup10)
# Chisq= 6.4  on 1 degrees of freedom, p= 0.0112 !!!!!!
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup10), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("DEXA", "MEPRED"), lty=1:2, title="Treatment Type:", bty="n")

subgroup11 <- subset(all_data, Milz >= 2.8 & Age >= 11.5, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup11)
# Chisq= 0  on 1 degrees of freedom, p= 0.869
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup11), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("DEXA", "MEPRED"), lty=1:2, title="Treatment Type:", bty="n")


# STIMA - DEXA hypotheses

subgroup4 <- subset(all_data, Milz < 2.8 & Leuc >= 3.5, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup4)
#Chisq= 0.8  on 1 degrees of freedom, p= 0.376
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup4), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("DEXA", "MEPRED"), lty=1:2, title="Treatment Type:", bty="n")

subgroup5 <- subset(all_data, Milz < 2.8 & Leuc < 2.2, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup5)
#Chisq= 5.8  on 1 degrees of freedom, p= 0.0163 !!!
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup5), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("DEXA", "MEPRED"), lty=1:2, title="Treatment Type:", bty="n")

subgroup6 <- subset(all_data, Milz >= 2.8 & Age < 6 & Age > 3.3, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup6)
#Chisq= 0.7  on 1 degrees of freedom, p= 0.399
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup6), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("DEXA", "MEPRED"), lty=1:2, title="Treatment Type:", bty="n")

subgroup7 <- subset(all_data, Milz >= 2.8 & Age < 3.3 & Leuc >= 16.1, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup7)
#Chisq= 2.6  on 1 degrees of freedom, p= 0.104
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup7), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("DEXA", "MEPRED"), lty=1:2, title="Treatment Type:", bty="n")

!!!
subgroup8 <- subset(all_data, Age < 2.9 & Leuc < 6.8, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup8)
#Chisq= 1.1  on 1 degrees of freedom, p= 0.286
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup8), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("DEXA", "MEPRED"), lty=1:2, title="Treatment Type:", bty="n")


# QUINT - DEXA hypotheses

subgroup8 <- subset(all_data, Age <= 2.83, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup8)
# Chisq= 3.1  on 1 degrees of freedom, p= 0.077 !!

subgroup9 <- subset(all_data, Age > 2.83 & Leber <= 1.25, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup9)
# Chisq= 0.7  on 1 degrees of freedom, p= 0.392

subgroup10 <- subset(all_data, Age > 2.83 & Leber > 1.25 & Age <= 10.63 & Milz <= 1.25, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup10)
# Chisq= 1.3  on 1 degrees of freedom, p= 0.255

subgroup11 <- subset(all_data, Age > 2.83 & Leber > 1.25 & Age <= 10.63 & Milz > 1.25 & Leuc > 10.5, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup11)
# Chisq= 0.2  on 1 degrees of freedom, p= 0.67

# QUINT - MePRED hypotheses

subgroup12 <- subset(all_data, Age > 2.83 & Leber > 1.25 & Age <= 10.63 & Milz > 1.25 & Leuc <= 10.5, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup12)
# Chisq= 1.7  on 1 degrees of freedom, p= 0.191

subgroup13 <- subset(all_data, Leber > 1.25 & Age > 10.63, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup13)
# Chisq= 2.5  on 1 degrees of freedom, p= 0.117 !



# QUINT - all data hypotheses ------

# DEXA:
subgroup21 <- subset(all_data, Age > 5.96 & Leber <= 1.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup21)
# Chisq= 1  on 1 degrees of freedom, p= 0.323
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup21), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("DEXA", "MEPRED"), lty=1:2, title="Treatment Type:", bty="n")

subgroup22 <- subset(all_data, Age <= 5.96 & Leber > 1.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup22)
# Chisq= 3.1  on 1 degrees of freedom, p= 0.0806 !!
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup22), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("DEXA", "MEPRED"), lty=1:2, title="Treatment Type:", bty="n")

# MEPRED:

subgroup23 <- subset(all_data, Age <= 5.96 & Leber <= 1.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup23)
# Chisq= 0  on 1 degrees of freedom, p= 0.984
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup23), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("DEXA", "MEPRED"), lty=1:2, title="Treatment Type:", bty="n")

subgroup24 <- subset(all_data, Age > 5.96 & Leber > 1.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup24)
# Chisq= 5  on 1 degrees of freedom, p= 0.0253 !!!
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup24), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("DEXA", "MEPRED"), lty=1:2, title="Treatment Type:", bty="n")


# ALL-2008 data experiments 


require("quint")
require("stima")
require("survival")

all_data <- read.csv("all.csv")

imrg_data <- subset(all_data, Riskgrup==2)
formula1 <- Time~Rand1 | Sex+Immun+CNS+Mediastinum+Age+Leuc+Leber+Milz
control1 <- quint::quint.control(maxl=10, Bootstrap=FALSE, dmin=0.1)

quint1<-quint::quint(formula1, data=imrg_data, control=control1)
plot(quint1)

# 400 hypothesis
subgroup1 <- subset(imrg_data, Leuc > 16.75 & Milz <= 3.25 & Leber <= 2.25, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.168
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("400", "500"), lty=1:2, title="Treatment Type:", bty="n")

# 500 hypothesis
subgroup2 <- subset(imrg_data, Leuc > 16.75 & Milz <= 3.25 & Leber > 2.25, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup2)
# p= 0.988
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup2), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("400", "500"), lty=1:2, title="Treatment Type:", bty="n")

# 400 hypothesis
subgroup1 <- subset(imrg_data, Leuc > 16.75 & Milz > 3.25, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("400", "500"), lty=1:2, title="Treatment Type:", bty="n")

# 500 hypothesis
subgroup2 <- subset(imrg_data, Leuc <= 16.75 & Leber <= 5.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup2)
# p= 0.344
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup2), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("400", "500"), lty=1:2, title="Treatment Type:", bty="n")

# 400 hypothesis
subgroup2 <- subset(imrg_data, Leuc <= 16.75 & Leber > 5.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup2)
# p= 0.612
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup2), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("400", "500"), lty=1:2, title="Treatment Type:", bty="n")


imrg_data_s <- imrg_data[,1:11]
stima1 <- stima::stima(imrg_data_s, 10, first=2)
plot(stima1, digits=2)
stima1_pr <- prune(stima1, imrg_data_s)

# 500 hypothesis
subgroup2 <- subset(imrg_data_s, Leuc <= 26, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup2)
# p= 0.344
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup2), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("400", "500"), lty=1:2, title="Treatment Type:", bty="n")

# 400 hypothesis
subgroup2 <- subset(imrg_data_s, Leuc >= 40, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup2)
# p= 0.344
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup2), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("400", "500"), lty=1:2, title="Treatment Type:", bty="n")

# 400 hypothesis
subgroup2 <- subset(imrg_data_s, Leber >= 4.2, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup2)
# p= 0.344
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup2), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("400", "500"), lty=1:2, title="Treatment Type:", bty="n")





srg_data <- subset(all_data, Riskgrup==1)
srg_data100200 <- subset(srg_data, Rand1 %in% c(100,200))
srg_data100300 <- subset(srg_data, Rand1 %in% c(100,300))
srg_data200300 <- subset(srg_data, Rand1 %in% c(200,300))

quint1<-quint::quint(formula1, data=srg_data100200, control=control1)
plot(quint1)

quint2<-quint::quint(formula1, data=srg_data100300, control=control1)
plot(quint2)

quint3<-quint::quint(formula1, data=srg_data200300, control=control1)
plot(quint3)

# 100 hypothesis
subgroup1 <- subset(srg_data100300, Leuc <= 9.95, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("100", "300"), lty=1:2, title="Treatment Type:", bty="n")

# 100 hypothesis
subgroup1 <- subset(srg_data100300, Leuc > 9.95 & Leber <= 2.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("100", "300"), lty=1:2, title="Treatment Type:", bty="n")

# 300 hypothesis
subgroup1 <- subset(srg_data100300, Leuc > 9.95 & Leber > 2.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("100", "300"), lty=1:2, title="Treatment Type:", bty="n")


# 200 - 300 hypotheses

# 300 hypothesis
subgroup1 <- subset(srg_data200300, Leuc <= 12.12 & Leber <= 2.75 & Age <= 4, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("200", "300"), lty=1:2, title="Treatment Type:", bty="n")

# 200 hypothesis
subgroup1 <- subset(srg_data200300, Leuc <= 12.12 & Leber <= 2.75 & Age > 4, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("200", "300"), lty=1:2, title="Treatment Type:", bty="n")

# 300 hypothesis
subgroup1 <- subset(srg_data200300, Leuc <= 12.12 & Leber > 2.75 & Leber <= 3.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("200", "300"), lty=1:2, title="Treatment Type:", bty="n")

# 200 hypothesis
subgroup1 <- subset(srg_data200300, Leuc <= 12.12 & Leber > 3.75 , select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("200", "300"), lty=1:2, title="Treatment Type:", bty="n")

# 300 hypothesis
subgroup1 <- subset(srg_data200300, Leuc > 12.12, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("200", "300"), lty=1:2, title="Treatment Type:", bty="n")





srg_data100200_s <- srg_data100200[,1:11]
srg_data100300_s <- srg_data100300[,1:11]
srg_data200300_s <- srg_data200300[,1:11]


stima1 <- stima::stima(srg_data100200_s, 7, first=2)
stima1_pr <- prune(stima1, srg_data100200_s)
plot(stima1, digits=1)

stima1 <- stima::stima(srg_data100300_s, 7, first=2)
stima1_pr <- prune(stima1, srg_data100300_s)
plot(stima1, digits=1)

stima1 <- stima::stima(srg_data200300_s, 7, first=2)
stima1_pr <- prune(stima1, srg_data200300_s)
plot(stima1, digits=1)

# 200 hypothesis
subgroup1 <- subset(srg_data100200_s, Age >= 3.4 & Age < 6 , select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("200", "300"), lty=1:2, title="Treatment Type:", bty="n")


# 300 hypothesis
subgroup1 <- subset(srg_data100300_s, Immun < 2.5 & Leuc >= 15.2 , select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("200", "300"), lty=1:2, title="Treatment Type:", bty="n")


# ALL-MB-2008 with EVT  results

#QUINT

all_data <- read.csv("evt.csv")
imrg_data <- subset(all_data, Riskgrup==2)
formula1 <- Time~Rand1 | Sex+ImmunCat+CNS+Mediastinum+age+Leuc+Leber+Milz
control1 <- quint::quint.control(dmin=0.1, Bootstrap=FALSE, crit="dm")

# ImRG  400-500 hypotheses

# 400 hypothesis
subgroup1 <- subset(imrg_data, Leuc > 16.75 & Milz <= 3.25 & Leber <= 2.25, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.168
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("400", "500"), lty=1:2, title="Treatment Type:", bty="n")

# 500 hypothesis
subgroup2 <- subset(imrg_data, Leuc > 16.75 & Milz <= 3.25 & Leber > 2.25, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup2)
# p= 0.988
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup2), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("400", "500"), lty=1:2, title="Treatment Type:", bty="n")

# 400 hypothesis
subgroup1 <- subset(imrg_data, Leuc > 16.75 & Milz > 3.25, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("400", "500"), lty=1:2, title="Treatment Type:", bty="n")

# 500 hypothesis
subgroup2 <- subset(imrg_data, Leuc <= 16.75 & Leber <= 5.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup2)
# p= 0.344
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup2), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("400", "500"), lty=1:2, title="Treatment Type:", bty="n")

# 400 hypothesis
subgroup2 <- subset(imrg_data, Leuc <= 16.75 & Leber > 5.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup2)
# p= 0.612
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup2), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("400", "500"), lty=1:2, title="Treatment Type:", bty="n")




# SRG 

srg_data <- subset(all_data, Riskgrup==1)
srg_data100200 <- subset(srg_data, Rand1 %in% c(100,200))
srg_data100300 <- subset(srg_data, Rand1 %in% c(100,300))
srg_data200300 <- subset(srg_data, Rand1 %in% c(200,300))

quint1<-quint::quint(formula1, data=srg_data100200, control=control1)
plot(quint1)

quint2<-quint::quint(formula1, data=srg_data100300, control=control1)
plot(quint2)

quint3<-quint::quint(formula1, data=srg_data200300, control=control1)
plot(quint3)

# SRG  100-300 hypotheses

# 100 hypothesis
subgroup1 <- subset(srg_data100300, age <= 3.59 & Leber <= 1.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("100", "300"), lty=1:2, title="Treatment Type:", bty="n")

# 100 hypothesis
subgroup1 <- subset(srg_data100300, age > 3.59 & age <= 7.28 & Leber <= 2.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("100", "300"), lty=1:2, title="Treatment Type:", bty="n")

# 100 hypothesis
subgroup1 <- subset(srg_data100300, age > 7.28 & Sex > 1.5, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("100", "300"), lty=1:2, title="Treatment Type:", bty="n")

# 300 hypothesis
subgroup1 <- subset(srg_data100300, age <= 3.59 & Leber > 1.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("100", "300"), lty=1:2, title="Treatment Type:", bty="n")

# 300 hypothesis
subgroup1 <- subset(srg_data100300, age > 7.28 & Sex < 1.5, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("100", "300"), lty=1:2, title="Treatment Type:", bty="n")


# SRG  200-300 hypotheses



# 300 hypothesis
subgroup1 <- subset(srg_data200300, age <= 2.89, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("200", "300"), lty=1:2, title="Treatment Type:", bty="n")

# 300 hypothesis
subgroup1 <- subset(srg_data200300, age > 2.89 & Leuc > 14.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("200", "300"), lty=1:2, title="Treatment Type:", bty="n")

# 200 hypothesis
subgroup1 <- subset(srg_data200300, age > 2.89 & Leuc <= 14.75, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup1)
# p= 0.934
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup1), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("200", "300"), lty=1:2, title="Treatment Type:", bty="n")


# ImRG 400-500 STIMA hypotheses

imrg_data_s <- imrg_data[,c(1,2,4:11)]
stima1 <- stima::stima(imrg_data_s, 10, first=2)
plot(stima1, digits=2)
stima1_pr <- prune(stima1, imrg_data_s)

# 500 hypothesis
subgroup2 <- subset(imrg_data_s, Leuc < 36.1 & age >= 2.08 & Milz < 2.5, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup2)
# p= 0.344
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup2), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("400", "500"), lty=1:2, title="Treatment Type:", bty="n")

# 400 hypothesis
subgroup2 <- subset(imrg_data_s, Milz >= 4.75 & age >= 3.97 & age < 5.27, select = c(Rand1, Tod, Time))
survdiff(Surv(Time, Tod) ~ Rand1, data=subgroup2)
# p= 0.344
plot(survfit(Surv(Time, Tod) ~ Rand1, data=subgroup2), main="Survival Rates",xlab="Survival Time in Days", ylab="Survival Rate", lty=1:2)
legend(500, 0.3, legend=c("400", "500"), lty=1:2, title="Treatment Type:", bty="n")



