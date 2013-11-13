#! /usr/bin/perl
#indicare OS con W.exe per windows L per Linux A per Apple

foreach(@ARGV){
if($_=~m/.+=/){
	@res=split('=',$_);
	#print "@res \n";
	$value{"$res[0]"}=$res[1];
	#$ou="$ou $res[1]";
}
}
#print %value;
open(file,"./res/file2");
open(file2,">> ./res/file2excec");

@file=<file>;

foreach(@file){
#print "$_";
if($_=~m/{.+}/){
do
{
$no="y";
(($var)=$_=~/{([A-Za-z]*)}/);
#print "$var\n";
$sost=$value{"$var"};
#print "$sost";
if(!($sost eq "")){
$newf=$_;
$newf=~s/({[A-Za-z]*})/$sost/;
$_=$newf;
}else{
print file2 "$newf";
$no="n";
}
}
while($no eq "y");
}else{print file2 "$_";}
}

close(file);
close(file2);
$numeroEnv=$value{"NENV"};
$NumeroSpazioSoluzioni=$numeroEnv*200+2;
@resSet=`./res/$value{"OS"} -filter=agent,base,dirt,obstacle -n=$NumeroSpazioSoluzioni ./res/file2excec`;
#`zenity --info`;
`rm ./res/file2excec`;

$points=int($NumeroSpazioSoluzioni/$numeroEnv);
$count=0;
while(($numeroEnv--)>0){
#print "($count*$points)*2\n";
print "$resSet[($count*$points)*2+2]";
$count++;
}


exit 0;

