#include<iostream>
#include<cstdio>
#include<cstring>
#include<cmath>
#include<cstdlib>
#include<climits>
#include<algorithm>
#include<vector>
#include<set>
#include<unordered_map>
#include<utility>
#include<queue>
#include<bitset>
#include<numeric>
#define ll long long
#define inf 0x3f3f3f3f
#define pb push_back
#define mp make_pair
#define ipair pair<ll,ll>
#define inp(a) scanf("%lld",&a)
#define out(a) printf("%lld\n",a)
#define allv(a) a.begin(),a.end()
#define alla(a,n) a,a+n
#define fi(i,a,b) for(ll i=a;i<b;i++)
#define N 100000
#define set(n,x) x|=(1<<n)
#define unset(n,x) x&=~(1<<n)
#define chkbit(n,x) (x&(1<<n))!=0
#define m(a,b) (a>b? b : a)
#define M(a,b) (a>b? a : b)
#define mod 1000000007
using namespace std;
unordered_map<string,set<string>> first;
unordered_map<string,set<string>> follow;

void printvec(unordered_map<string,set<string>> mp) {
    for(auto it=mp.begin();it!=mp.end();it++) {
        string st=it->first;
        if(mp[st].size()>0) {
            if(islower(st[0]))
                continue;
            cout<<it->first<<" = { ";
            ll sz=(ll)(it->second).size();
            for(auto vit=(it->second).begin();vit!=(it->second).end();vit++) {
                if(sz==1)
                    cout<<*vit<<" } "<<endl;
                else
                    cout<<*vit<<" , ";
                sz--;
            }
            //cout<<"} "<<endl;
            cout<<endl;
        }
    }
}

void firsts(unordered_map<string,vector<string>> mp) {

    //Dealing with all the terminals and ebsolon transitions
    for(auto it=mp.begin();it!=mp.end();it++) {
        vector<string> te=it->second;
        for(auto vit=te.begin();vit!=te.end();vit++) {
            string st=*vit;
            ll l=st.length();
            ll put=0;
            ll i;
            //cout<<st<<": ";
            fi(i,0,l) {
                if(isalpha(st[i]) && islower(st[i])) {
                    string st2="";
                    st2+=st[i];
                    //cout<<st2<<" "<<put<<" ";
                    if(put==0) {
                        first[it->first].insert(st2);
                        put=1;
                    }
                    first[st2].insert(st2);
                }
                if(st=="@")
                    first[it->first].insert(st);
                //cout<<put<<endl;
            }
        }
    }

    //Handling all the terminal transitions  
    for(auto it=mp.begin();it!=mp.end();it++) {
        string st=it->first;
        vector<string> te=it->second;
        for(auto vit=te.begin();vit!=te.end();vit++) {
            string st1=*vit;
            ll l=st1.length();
            ll i;
            set<string> se;
            fi(i,0,l) {
                if(isalpha(st1[i]) && !islower(st1[i])) {
                    string st2="";
                    st2+=st1[i];
                    first[st].insert(first[st2].begin(),first[st2].end());
                    if(first[st2].find("@")==first[st2].end())
                        break;
                }
                else if(islower(st1[i]))
                    break;
            }
        }
    }

}

void follows1(unordered_map<string,vector<string>> mp) {
    ll folSfound=0;
    for(auto bit=mp.begin();bit!=mp.end();bit++) {
        vector<string> te=bit->second;
        for(auto dit=te.begin();dit!=te.end();dit++) {
            string st=*dit;
            ll l=st.length();
            ll i;
            fi(i,0,l) {
                string st1="";
                st1+=st[i];
                if(folSfound) {
                    string S="S";
                    follow[S].insert(first[st1].begin(),first[st1].end());
                    if(first[st1].find("@")==first[st1].end())
                        break;
                }
                if(folSfound==0 && st[i]=='S') 
                    folSfound=1;
            }
        }
    }
    if(folSfound==0)
        follow["S"].insert("$");
    ll z=0;
    while(z<2) {
        for(auto it=mp.begin();it!=mp.end();it++) {
            string nterm=it->first;
            for(auto vit=mp.begin();vit!=mp.end();vit++) {
                vector<string> te=vit->second;
                string mprod=vit->first;
                for(auto sit=te.begin();sit!=te.end();sit++) {
                    string prod=*sit;
                    ll l=prod.length();
                    ll i;
                    i=0;
                    ll folfound=0;
                    while(i<l) {
                        string st="";
                        st+=prod[i];
                        if(folfound) {
                            follow[nterm].insert(first[st].begin(),first[st].end());
                            if(first[st].find("@")==first[st].end()) 
                                break;
                            else {
                                if((i+1)==l) {
                                    follow[nterm].insert(follow[mprod].begin(),follow[mprod].end());
                                    follow[nterm].erase("@");
                                }
                            }
                        }
                        if(folfound==0 && st==nterm) {
                            if((i+1)==l) {
                                follow[nterm].insert(follow[mprod].begin(),follow[mprod].end());
                                follow[nterm].erase("@");
                            }
                            folfound=1;
                        }
                        i++;
                    }
                }
            }
        }
        z++;
    }
}

int main()
{
    ll i,j,k,l,e,n,m,t,f,c;
    inp(n);
    cout<<"Enter no of productions: "<<n<<endl<<endl;
    unordered_map<string,vector<string>> mp;
    string s,d;
    cout<<"Enter productions: "<<endl;
    fi(i,0,n) {
        cin>>s>>d;
        mp[s].push_back(d);
    }
    for(auto it=mp.begin();it!=mp.end();it++) {
        cout<<it->first<<" -> ";
        for(auto vit=(it->second).begin();vit!=(it->second).end();vit++) {
            if((vit+1)==(it->second).end())
                cout<<*vit<<endl;
            else
                cout<<*vit<<" | ";
        }
        //cout<<endl;
    }
    cout<<endl;
    //Constructing firsts
    cout<<"First sets"<<endl;
    firsts(mp);
    printvec(first);
    cout<<endl;

    //Constructing follows
    cout<<"Follow sets"<<endl;
    //follows(mp);
    follows1(mp);
    for(auto it=follow.begin();it!=follow.end();it++) {
        set<string> se= it->second;
        if(se.find("@")!=se.end())
            se.erase("@");
    }
    printvec(follow);
    return 0;
}

