cd ../app/libs
rm -r -f .git
git init
git remote add -f origin http://git.intra.weibo.com/kuran/krcom-andriod-sdk.git
git config core.sparsecheckout true
echo aar_release >> .git/info/sparse-checkout
git pull origin master